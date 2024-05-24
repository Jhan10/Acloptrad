package com.geanjesus.acloptrad.entities;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geanjesus.acloptrad.entities.AdviceAPI.ResponseContent;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// DadJoke API
public class DadJoke {
	//@JsonProperty
	//@JsonProperty("slip")
	//@JsonIgnoreProperties(ignoreUnknown = true)
	
	@JsonAutoDetect
	public static class ResponseDadJoke {
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getJoke() {
			return joke;
		}
		public void setJoke(String joke) {
			this.joke = joke;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		
		@JsonProperty
		private String id;
		@JsonProperty
		private String joke;
		@JsonProperty
		private Integer status;
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static String getRandomDadJoke(String url) {
		ObjectMapper mapper = new ObjectMapper();
		OkHttpClient client = new OkHttpClient();
		
		ResponseDadJoke responseDadJoke=null;
		
		Request request = new Request.Builder()
			      .url(url)
			      .header("Accept", "application/json")
			      .build();
		
		try (Response response = client.newCall(request).execute()) {
			  
			responseDadJoke=mapper.readValue(response.body().string(), ResponseDadJoke.class);  
			
			return responseDadJoke.joke.toString();
			  
		  }catch (StreamReadException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		  }catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
		
	}
}
