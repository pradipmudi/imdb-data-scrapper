package com.sezzle.imdb.scrapper.util;

import com.google.gson.Gson;

public class JsonUtil {
	
	private Gson gson = null;

	private static JsonUtil jsonUtil;

	public static JsonUtil getInstance() {
		if (jsonUtil == null)
			jsonUtil = new JsonUtil();
		return jsonUtil;
	}

	public JsonUtil() {
		gson = new Gson();
	}

	public String toJson(Object object) {
//		log.info("In toJson for object : "+ object);
		return gson.toJson(object);
	}

}
