package com.geanjesus.acloptrad.entities;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//Useless Fact API
public class UF {

	@JsonAutoDetect
	public static class ResponseFact{
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getSource_url() {
			return source_url;
		}
		public void setSource_url(String source_url) {
			this.source_url = source_url;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getPermalink() {
			return permalink;
		}
		public void setPermalink(String permalink) {
			this.permalink = permalink;
		}
		@JsonProperty
		private String id;
		@JsonProperty
		private String text;
		@JsonProperty
		private String source;
		@JsonProperty
		private String source_url;
		@JsonProperty
		private String language;
		@JsonProperty
		private String permalink;
	}
	//@JsonProperty
	//@JsonProperty("slip")
	//@JsonIgnoreProperties(ignoreUnknown = true)
	
	public static String getRandomUFact(String url) {
		ObjectMapper mapper = new ObjectMapper();
		OkHttpClient client = new OkHttpClient();
		
		ResponseFact responseFact=null;
		
		Request request = new Request.Builder()
			      .url(url)
			      .build();
		
		try (Response response = client.newCall(request).execute()) {
			  
			responseFact=mapper.readValue(response.body().string(), ResponseFact.class);  
			
			return responseFact.text.toString();
			  
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
