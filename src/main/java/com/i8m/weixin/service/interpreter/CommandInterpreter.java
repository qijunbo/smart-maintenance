package com.i8m.weixin.service.interpreter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i8m.repository.URL;
import com.i8m.weixin.service.event.MsgEvent;
import com.i8m.weixin.service.message.MessageService;
import com.i8m.weixin.service.print.PrintService;
import com.i8m.weixin.service.register.RegisterService;

@Service
public class CommandInterpreter {

	@Autowired
	private MessageService service;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private PrintService printService;

	public void interpret(MsgEvent event) {

		StringBuffer content = new StringBuffer(event.getContent().trim());
		String cmd = content.substring(0, content.indexOf(" "));
		String parms = content.substring(content.indexOf(" ")).trim();
		Commands command = Commands.err;
		try {
			command = Commands.valueOf(cmd);
		} catch (Exception e) {
			service.sendHelp(event);
			return;
		}

		switch (command) {
		case add:
			processAdd(event, parms);
			break;
		case del:
			processDel(event, parms);
			break;
		default:
			service.sendHelp(event);
			break;
		}

	}

	private void processAdd(MsgEvent event, String parms) {
		String[] d = parms.split(",");

		if (d.length < 3) {
			service.sendHelp(event);
		}
		URL url = new URL();
		url.setService(d[0].trim());
		url.setUrl(d[1].trim());
		url.setDescription(d[2]);
		// 30 days
		url.setExpiredate(System.currentTimeMillis() + 2592000000L);

		service.sendAnyThing(event.getFromUserName(),
				printService.printServiceOfUser(registerService.addURLToUser(event.getFromUserName(), url)));

	}

	private void processDel(MsgEvent event, String parms) {
		service.sendAnyThing(event.getFromUserName(),
				printService.printServiceOfUser(registerService.removeURLFromUser(event.getFromUserName(), parms)));

	}

}
