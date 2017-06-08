package com.i8m.weixin.service.print;

import java.util.List;

import org.springframework.stereotype.Service;

import com.i8m.repository.URL;
import com.i8m.repository.User;
import com.i8m.weixin.service.monitor.MonitorResult;

@Service
public class PrintService {

	public String printServiceOfUser(User user) {

		StringBuffer buffer = new StringBuffer();
		 
		buffer.append( user.getWeixin() + " 已注册" + user.getUrls().size() + "个服务：\n");
		for (URL url : user.getUrls()) {
			buffer.append(url.getService() + "\t" + url.getUrl() + "\n");
		}

		return buffer.toString();
	}
	
	
	
	public String printServiceStatusOfUser(String weixin, List<MonitorResult> report) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("你好, " +  weixin  + ", 当前服务器状态如下：\n");
		for (MonitorResult item : report) {
			buffer.append( item.getService() + ": " + item.getStatus().name() + "\n");
		}

		return buffer.toString();
	}

}
