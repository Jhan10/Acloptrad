package com.geanjesus.acloptrad.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geanjesus.acloptrad.entities.DeepL.TextToTranslate;
import com.geanjesus.acloptrad.entities.DeepL.TranslationsHelper;
import com.geanjesus.acloptrad.entities.TranslateCenter; 

@RestController
public class TranslateController {


	@PostMapping
	@RequestMapping(value="/Translate")
	public String getText(@RequestBody TextToTranslate textToTranslate){
		String translatedTex=TranslateCenter.Translate(textToTranslate);
		JSONObject json = new JSONObject(translatedTex);  

		return json.toString();

	}
	
	@PostMapping
	@RequestMapping(value="/TranslateMapped")
	public String getTextMapped(@RequestBody TextToTranslate textToTranslate){
		TranslationsHelper translated =TranslateCenter.TranslateMapped(textToTranslate);
		JSONObject json = new JSONObject(translated);  

		return translated.getTranslact().get(0).text;

	}
}
