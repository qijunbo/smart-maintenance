package com.i8m.weixin.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i8m.configure.PropertiesConfigure;
import com.i8m.repository.URL;
import com.i8m.repository.User;
import com.i8m.repository.UserRepository;
import com.i8m.weixin.service.message.MessageFactory;
import com.i8m.weixin.service.message.MessageService;

@Service
public class RegisterService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private MessageService service;

	@Autowired
	private PropertiesConfigure configure;

	@Autowired
	private MessageFactory factory;

	public User addURLToUser(String weixin, URL url) {

		User user = null;
		if (repository.exists(weixin)) {

			user = repository.findOne(weixin);
		} else {
			user = new User(weixin);
		}

		user.getUrls().add(url);

		return repository.save(user);

	}

	public User removeURLFromUser(String weixin, String service) {

		User user = null;
		if (repository.exists(weixin)) {
			user = repository.findOne(weixin);
		} else {
			user = new User(weixin);
		}

		for (URL url : user.getUrls()) {
			if (url.getService().equalsIgnoreCase(service)) {
				user.getUrls().remove(url);
				break;
			}
		}

		return repository.save(user);

	}

}
