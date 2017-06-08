package com.i8m.health.web.ui.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.i8m.common.util.WebRequestReader;
import com.i8m.repository.User;
import com.i8m.repository.UserRepository;
import com.i8m.weixin.service.monitor.MonitorResult;
import com.i8m.weixin.service.monitor.MontiorService;
import com.i8m.weixin.service.print.PrintService;

@Controller
public class VMStatusController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MontiorService service;

	@Autowired
	private PrintService printService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/vm/status/{weixin}", method = RequestMethod.POST)
	public String root(@RequestBody(required = false) String body) {
		if (log.isDebugEnabled()) {
			log.debug("\nHttp Post from wexin Server: #[" + body + "]#");
		}
		return "greeting";
	}

	@RequestMapping(value = "/vm/status/{weixin}", method = RequestMethod.GET)
	public String getVMStatus(WebRequest request, Model model, @PathVariable String weixin) {

		if (log.isDebugEnabled()) {
			log.debug("\nHttp Get from wexin Server: #[" + WebRequestReader.readlog(request) + "]#");
		}

		User user = userRepository.findOne(weixin);
		List<MonitorResult> vmStatusOfUser = service.getVMStatusOfUser(user);

		if (user.getTag() == null) {
			model.addAttribute("owner", weixin);
		} else {
			model.addAttribute("owner", user.getTag());
		}
		model.addAttribute("result", vmStatusOfUser);

		return "greeting";
	}

}
