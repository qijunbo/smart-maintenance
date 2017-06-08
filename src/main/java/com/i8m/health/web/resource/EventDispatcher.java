package com.i8m.health.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i8m.repository.User;
import com.i8m.repository.UserRepository;
import com.i8m.weixin.service.event.MsgEvent;
import com.i8m.weixin.service.interpreter.CommandInterpreter;
import com.i8m.weixin.service.menu.ScanQRcodeService;
import com.i8m.weixin.service.message.MessageFactory;
import com.i8m.weixin.service.message.MessageService;
import com.i8m.weixin.service.monitor.MontiorService;
import com.i8m.weixin.service.register.RegisterService;

@Service
public class EventDispatcher {

	@Autowired
	private MessageService service;

	@Autowired
	private CommandInterpreter interpreter;

	@Autowired
	private RegisterService registerService;

	@Autowired
	private MessageFactory factory;

	@Autowired
	private MontiorService monitorService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScanQRcodeService scanQRcodeService;

	public void dispatch(MsgEvent event) {

		if (MsgType.text.name().equals(event.getMsgType())) {
			handleCommand(event);

		} else if (MsgType.event.name().equals(event.getMsgType())) {
			handleMenu(event);
		} else {
			// TODO for now, nothing todo.
		}

	}

	private void handleCommand(MsgEvent event) {
		interpreter.interpret(event);
	}

	private void handleMenu(MsgEvent event) {

		if (MenuKey.vmstatus.name().equals(event.getEventKey())) {

			User user = userRepository.findOne(event.getFromUserName());
			if (user == null) {
				service.sendHelp(event);
			} else {
				monitorService.createVMStatusLink(user);
			}

		} else if (MenuKey.donate.name().equals(event.getEventKey())) {
			service.sendDonate(event);
		} else if (MenuKey.feedback.name().equals(event.getEventKey())) {
			sendDefault(event);
		} else if (MenuKey.help.name().equals(event.getEventKey())) {
			service.sendHelp(event);
		} else if (MenuKey.addvm.name().equals(event.getEventKey())) {
			scanQRcodeService.processAdd(event);
		} else if (MenuKey.group.name().equals(event.getEventKey())) {
			scanQRcodeService.processAdd(event);
		} else {
			sendDefault(event);
		}

	}

	private void sendDefault(MsgEvent event) {
		service.sendAnyThing(event.getFromUserName(), "你好， " + event.getFromUserName() + ", 谢谢关注云运维, 系统正在完善中。");
	}

}
