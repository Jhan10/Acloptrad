package com.geanjesus.acloptrad.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geanjesus.acloptrad.entities.AdviceAPI;
import com.geanjesus.acloptrad.entities.DeepL;
import com.geanjesus.acloptrad.entities.DeepL.TextToTranslate;
import com.geanjesus.acloptrad.entities.TranslateCenter;

@RestController
public class AdviceController {

	@GetMapping
	@RequestMapping(value="/advice")
	public String getAdvice() throws IOException {
		String respondedAdvice = null;
		DeepL.TextToTranslate textToTranslate;
		String translatedAdvice = null;
		
	try {
		respondedAdvice = AdviceAPI.getRandomAdvice("https://api.adviceslip.com/advice");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	if (respondedAdvice.isEmpty()) throw new IOException("Unexpected code $response");
		
	try {
			 textToTranslate = new DeepL.TextToTranslate("PT",new String[]{respondedAdvice});
			 translatedAdvice = TranslateCenter.Translate(textToTranslate);
			
			 return TranslateCenter.Rights(translatedAdvice);
		} catch (Exception e) {
			
			e.printStackTrace();
		};
	
	
	
	
	return null;
	
	}
}
