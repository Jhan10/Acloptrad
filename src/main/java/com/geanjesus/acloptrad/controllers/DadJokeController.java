package com.geanjesus.acloptrad.controllers;

import java.io.IOException;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geanjesus.acloptrad.entities.DadJoke;
import com.geanjesus.acloptrad.entities.DeepL;
import com.geanjesus.acloptrad.entities.TranslateCenter;

@RestController
public class DadJokeController {

	@GetMapping
	@RequestMapping(value="/DJoke")
	public String getDJoke() throws IOException {
		String respondedDJoke = null;
		DeepL.TextToTranslate textToTranslate;
		String translatedDJoke = null;
		
	try {
		respondedDJoke = DadJoke.getRandomDadJoke("https://icanhazdadjoke.com/");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	if (StringUtils.isEmpty(respondedDJoke)) throw new IOException("Unexpected code $response");
		
	try {
			 textToTranslate = new DeepL.TextToTranslate("PT",new String[]{respondedDJoke});
			 translatedDJoke = TranslateCenter.Translate(textToTranslate);
			
			 return TranslateCenter.Rights(translatedDJoke);
		} catch (Exception e) {
			
			e.printStackTrace();
		};
	
	
	
	
	return null;
	
	}
}
