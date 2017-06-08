package com.i8m.weixin.base.service.media;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i8m.common.http.HttpPostClient;
import com.i8m.configure.PropertiesConfigure;
import com.i8m.weixin.service.token.AccessTokenService;

/**
 * From:
 * http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%8A%E4%BC%A0%E4%B8
 * %B4%E6%97%B6%E7%B4%A0%E6%9D%90%E6%96%87%E4%BB%B6
 * 
 * https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&
 * type=TYPE
 * 
 * @author qijunbo
 *
 */
@Service
public class MediaService {

	@Value("${weixin.media.list.url}")
	private String listURL;

	@Value("${weixin.media.temp.upload.url}")
	private String uploadTempImageURL;

	@Autowired
	private AccessTokenService service;

	@Autowired
	private HttpPostClient httpClient;

	@Autowired
	private PropertiesConfigure configure;

	@Autowired
	private MediaResponseHandler handler;

	@Autowired
	private ListResponseHandler listHandler;

	public MediaResponse upload(MediaType type, File file) throws Exception {

		String uri = String.format(uploadTempImageURL, service.getAccessToken(), type.name());
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		entityBuilder.addPart(file.getName(), new FileBody(file));
		HttpEntity entity = entityBuilder.build();
		return httpClient.execute(uri, entity, handler);

	}

	public ListResponse listMedia(MediaType type, int offset, int count) throws Exception {
		String uri = String.format(listURL, service.getAccessToken());
		GetListRequest request = new GetListRequest(type, 1, offset, count);
		return httpClient.execute(uri, request, listHandler);

	}
}
