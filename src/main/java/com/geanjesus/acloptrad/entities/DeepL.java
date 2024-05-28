package com.geanjesus.acloptrad.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation. JsonProperty;


@JsonAutoDetect
public class DeepL {
	
	@JsonAutoDetect
	public static class translations{
		
		private TranslationsHelper translationsHelper;
		
		//@JsonProperty("translations")
		public TranslationsHelper getTranslationsHelper() {
			return translationsHelper;
		}
		//@JsonProperty("translations")
		public void setTranslationsHelper(TranslationsHelper translationsHelper) {
			this.translationsHelper = translationsHelper;
		}

		
	}
	@JsonAutoDetect
	public static class Translact{
		@JsonProperty
		public String detected_source_language;
		@JsonProperty
		public String text="";
	}
	
	public static class TranslationsHelper{
		 
		
		private  List<Translact> translact;

		@JsonProperty("translations")
		public  List<Translact> getTranslact() {
			return translact;
		}
		
		
		public void setTranslact(List<Translact> translact) {
			this.translact = translact;
		}
	}
	
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class TextToTranslate{
			@JsonIgnoreProperties(ignoreUnknown = true)
			public String target_lang ="PT";
			@JsonIgnoreProperties(ignoreUnknown = true)
			public String[] text= null;
			
			public TextToTranslate(String target_lang, String[] text) {
				this.target_lang=target_lang;
				this.text=text;
			}
		}

	
}
