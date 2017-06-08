package com.i8m.weixin.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i8m.repository.URL;
import com.i8m.weixin.service.event.MsgEvent;
import com.i8m.weixin.service.message.MessageService;
import com.i8m.weixin.service.print.PrintService;
import com.i8m.weixin.service.register.RegisterService;

@Service
public class ScanQRcodeService {

	@Autowired
	private MessageService service;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private PrintService printService;

	public void processAdd(MsgEvent event) {

		URL url = new URL();
		url.setService(event.getScanCodeInfo().getScanResult());
		url.setUrl(event.getScanCodeInfo().getScanResult());
		url.setDescription(event.getScanCodeInfo().getScanResult());
		// 30 days
		url.setExpiredate(System.currentTimeMillis() + 2592000000L);

		service.sendAnyThing(event.getFromUserName(),
				printService.printServiceOfUser(registerService.addURLToUser(event.getFromUserName(), url)));

	}

}
