package com.geanjesus.acloptrad.entities;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.geanjesus.acloptrad.entities.DeepL.TextToTranslate;
import com.geanjesus.acloptrad.entities.DeepL.TranslationsHelper;
import com.geanjesus.acloptrad.entities.DeepL.translations;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.*;

public class TranslateCenter {
	
	public static String Translate(TextToTranslate contentToTranslate) {
		  
		OkHttpClient client = new OkHttpClient();
		String url="https://api-free.deepl.com/v2/translate";
		//String json="{\"text\":[\"What are you doing?\"],\"target_lang\":\"PT\"}";
		final MediaType JSON = MediaType.get("application/json");
		TranslationsHelper result = null;
		ObjectMapper mapper = new ObjectMapper();
		String cttStr=null;
		try {
			cttStr = new ObjectMapper().writeValueAsString(contentToTranslate);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		RequestBody body = RequestBody.create(JSON, cttStr);
		Request request = new Request.Builder()
		      .url(url)
		      .header("Authorization", "DeepL-Auth-Key 6b556830-1817-4988-9d46-32a9e019f851:fx")
		      .post(body)
		      .build();
		  try (Response response = client.newCall(request).execute()) {
			  
			  	result=mapper.readValue(response.body().string(), TranslationsHelper.class);  
				return result.getTranslact().get(0).text;
			  
			 
		  }catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static TranslationsHelper TranslateMapped(TextToTranslate contentToTranslate) {
		final Moshi moshi = new Moshi.Builder().build();
		  final JsonAdapter<translations> gistJsonAdapter = moshi.adapter(translations.class);
		  
		  ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE); //turn off everything
			mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY); // only use fields
			//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			//mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			
			TranslationsHelper result = null;
			OkHttpClient client = new OkHttpClient();
			String url="https://api-free.deepl.com/v2/translate";
			String json="{\"text\":[\"What are you doing?\"],\"target_lang\":\"PT\"}";
			final MediaType JSON = MediaType.get("application/json");
			RequestBody body = RequestBody.create(JSON, json);
			
			Request request = new Request.Builder()
				      .url(url)
				      .header("Authorization", "DeepL-Auth-Key 6b556830-1817-4988-9d46-32a9e019f851:fx")
				      .post(body)
				      .build();
			try (Response response = client.newCall(request).execute()) {
				  
				result=mapper.readValue(response.body().string(), TranslationsHelper.class);  
				return result;
				  
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
			
				//result.add();
			  //Object obj =response.body().byteStream();
			 //ResultContent.Translact trasn = gistJsonAdapter.fromJson(response.body().source());
			
			return null;
	}
	
	public static String Rights(String contentReturn) {
		return contentReturn+"\n\n Acloptrad \n ©2024 Araquari-SC \n All Rights Reserved.";
	}
}
