package com.config;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Jsonconfig
{
	   @Bean
	   public JSONObject jsonObject() {
		  JSONObject jsonObject = new JSONObject();
	      return jsonObject;	      
	   }
}
