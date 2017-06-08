package com.i8m;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@Configuration
public class AppConfiguration {

	@Bean
	Gson gson() {
		return new Gson();
	}

	@Bean
	XStream xstream() {
		return new XStream();
	}

	@Bean
	SimpleDateFormat formatter() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss 'Z'X");
	}

}
