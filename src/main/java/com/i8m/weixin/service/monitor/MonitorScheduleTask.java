package com.i8m.weixin.service.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.i8m.repository.User;
import com.i8m.repository.UserRepository;
import com.i8m.weixin.service.message.MessageService;
import com.i8m.weixin.service.print.PrintService;

@Configuration
@EnableAsync
@EnableScheduling
public class MonitorScheduleTask {

	@Autowired
	private UserRepository repository;

	@Autowired
	private MontiorService service;

	@Autowired
	private MessageService msgService;

	@Autowired
	private PrintService printService;

	@Scheduled(initialDelay = 20000, fixedRate = 900000)
	public void scanMonitorTask() {
		Iterable<User> users = repository.findAll();
		for (User user : users) {

			List<MonitorResult> result = service.getVMStatusOfUser(user);

			for (MonitorResult item : result) {
				// notify user or their group if any of the server went wrong.
				if (MonitorResult.Status.Failed.equals(item.getStatus())) {
					String serviceStatusOfUser = printService.printServiceStatusOfUser(user.getWeixin(), result);
					if (!user.getTag().isEmpty()) {
						msgService.sendMessageToGroup(user.getTag(), serviceStatusOfUser);
					} else {
						msgService.sendAnyThing(user.getWeixin(), serviceStatusOfUser);
					}
					break;
				}
			}
		}
	}

}
