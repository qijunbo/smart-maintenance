package com.i8m.weixin.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;

@Service
public class XMLObjectEnDecoder {

	@Autowired
	private XStream xstream;

	@SuppressWarnings("unchecked")
	public <T> T decode(String xml, Class<T> clazz) {

		xstream.processAnnotations(clazz);
		return (T) xstream.fromXML(xml);

	}

	public String encode(Object obj) {
		xstream.alias("xml", obj.getClass());
		String reply = xstream.toXML(obj);

		reply = reply.replace("&lt;", "<");
		reply = reply.replace("&gt;", ">");
		reply = reply.replace("\n", "");

		return reply;

	}

}
