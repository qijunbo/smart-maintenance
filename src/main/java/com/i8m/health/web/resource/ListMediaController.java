package com.i8m.health.web.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i8m.weixin.base.service.media.ListResponse;
import com.i8m.weixin.base.service.media.MediaService;
import com.i8m.weixin.base.service.media.MediaType;

@RestController
public class ListMediaController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MediaService mediaService;

	@RequestMapping(value = "/media/{type}/{offset}/{count}", method = RequestMethod.GET)
 
	public ListResponse root(@PathVariable MediaType type, @PathVariable int offset, @PathVariable int count)
			throws Exception {
		return mediaService.listMedia(type, offset, count);
	}
}
