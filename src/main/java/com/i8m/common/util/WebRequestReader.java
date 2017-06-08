package com.i8m.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;

import org.springframework.web.context.request.WebRequest;

public class WebRequestReader {

	public static String read(WebRequest request) {

		Iterator<String> params = request.getParameterNames();
		StringBuffer sb = new StringBuffer();
		try {
			while (params.hasNext()) {
				String name = params.next();
				sb.append(name + "=" + URLDecoder.decode(request.getParameter(name), "utf-8") + "&");
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return sb.toString().trim();

	}

	public static String readlog(WebRequest request) {
		Iterator<String> params = request.getParameterNames();
		StringBuffer sb = new StringBuffer();
		while (params.hasNext()) {
			String name = params.next();
			sb.append(name + "=" + request.getParameter(name) + '\n');
		}
		return sb.toString().trim();
	}

}
