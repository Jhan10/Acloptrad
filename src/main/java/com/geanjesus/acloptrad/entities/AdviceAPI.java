package com.geanjesus.acloptrad.entities;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


// Advice API
@JsonAutoDetect
public class AdviceAPI {
	
	@JsonAutoDetect
	public static class ResponseContent {

		@JsonProperty("slip")
		private static slip slip;
		
		@JsonProperty("slip")
		public slip getResponseContent() {
			return slip;
		}

		@JsonProperty("slip")
		public void setResponseContent(slip slip) {
			this.slip = slip;
		}


		@JsonAutoDetect
		public static class slip{
			@JsonProperty("id")
			private Integer slip_id;
			@JsonProperty
			private String advice;
			
			public Integer getSlip_id() {
				return slip_id;
			}
			public void setSlip_id(Integer slip_id) {
				this.slip_id = slip_id;
			}
			public String getAdvice() {
				return advice;
			}
			public void setAdvice(String advice) {
				this.advice = advice;
			}
			
			
		}
	}


	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static String getRandomAdvice(String url) {
		ObjectMapper mapper = new ObjectMapper();
		OkHttpClient client = new OkHttpClient();
		
		ResponseContent responseContent=null;
		
		Request request = new Request.Builder()
			      .url(url)
			      .build();
		
		try (Response response = client.newCall(request).execute()) {
			  
			responseContent=mapper.readValue(response.body().string(), ResponseContent.class);  
			
			return responseContent.slip.advice.toString();
			  
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
