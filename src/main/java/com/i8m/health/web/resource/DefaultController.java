package com.i8m.health.web.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.Gson;
import com.i8m.AppContext;
import com.i8m.common.util.DomianNameUtil;
import com.i8m.common.util.WebRequestReader;
import com.i8m.weixin.service.event.MsgEvent;
import com.i8m.weixin.service.event.MsgResponse;
import com.i8m.weixin.service.event.XMLObjectEnDecoder;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@RestController
public class DefaultController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WXBizMsgCrypt crypt;

	@Autowired
	private Gson gson;

	@Autowired
	private XMLObjectEnDecoder enDecoder;

	@Autowired
	private EventDispatcher dispatcher;

	@Autowired
	private AppContext context;

	@Autowired
	private DomianNameUtil domianNameUtil;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(WebRequest request) throws AesException {

		initializeServer();

		if (log.isDebugEnabled()) {
			log.debug("\nHttp Get from wexin Server: #[" + WebRequestReader.readlog(request) + "]#");
		}

		String msg_signature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		if (msg_signature == null || timestamp == null || nonce == null || echostr == null) {
			return "failed";
		}

		try {

			String sEchoStr = crypt.VerifyURL(msg_signature, timestamp, nonce, echostr);

			if (log.isDebugEnabled()) {
				log.debug("verifyurl echostr: " + sEchoStr);
			}

			return sEchoStr;

		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("failed: " + e.getMessage());
			}
			e.printStackTrace();
		}

		return "failed";

	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String portal(WebRequest request, @RequestBody(required = false) String body) throws AesException {

		initializeServer();

		if (log.isDebugEnabled()) {
			log.debug("\nHttp Post From wexin Server: #[" + WebRequestReader.readlog(request) + "]#");
		}

		if (log.isDebugEnabled()) {
			if (!body.isEmpty()) {
				log.debug("\nBody: \n" + body);
			}
		}

		String msg_signature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		log.debug("\nIf you wana test on local host.\nhttp://localhost:8080?msg_signature=" + msg_signature
				+ "&timestamp=" + timestamp + "&nonce=" + nonce);

		if (msg_signature == null || timestamp == null || nonce == null) {
			return "failed";
		}

		try {
			String xml = crypt.DecryptMsg(msg_signature, timestamp, nonce, body);
			System.out.println("after decrypt msg: " + xml);

			MsgEvent msgEvent = enDecoder.decode(xml, MsgEvent.class);
			if (log.isDebugEnabled()) {
				log.debug(gson.toJson(msgEvent));
			}

			new Thread(new Runnable() {
				@Override
				public void run() {
					dispatcher.dispatch(msgEvent);
				}
			}).start();

			return "";

		} catch (Exception e) {

			e.printStackTrace();

			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
		}

		throw new RuntimeException("Unknow exception.");

	}

	private void initializeServer() {

		if (context.get(AppContext.DomainName) == null) {
			context.put(AppContext.DomainName, domianNameUtil.getDomainName());
		}
	}

	// Weixin dropped this methodology
	private void createXMLResponse(String nonce, MsgEvent msgEvent) throws AesException {
		MsgResponse response = new MsgResponse();

		response.setToUserName(msgEvent.getFromUserName());

		if (msgEvent.getEventKey() != null) {
			response.setContent(msgEvent.getEventKey());
		} else if (msgEvent.getContent() != null) {
			response.setContent(msgEvent.getContent());
		} else {
			response.setContent("Pardon ?");
		}

		long time = System.currentTimeMillis() / 1000;
		response.setCreateTime(time);
		response.setMsgType(MsgType.text.name());

		String reply = enDecoder.encode(response);

		if (log.isDebugEnabled()) {
			log.debug("Reply to wexin plain message: " + reply);
		}

		reply = crypt.EncryptMsg(reply, String.valueOf(time), nonce);

		if (log.isDebugEnabled()) {
			log.debug("Reply to wexin encrypted message: " + reply);
		}
	}
}
