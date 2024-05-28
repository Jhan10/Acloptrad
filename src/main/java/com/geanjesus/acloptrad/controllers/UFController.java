package com.geanjesus.acloptrad.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geanjesus.acloptrad.entities.DeepL;
import com.geanjesus.acloptrad.entities.TranslateCenter;
import com.geanjesus.acloptrad.entities.UF;

@RestController
public class UFController {

	@GetMapping
	@RequestMapping(value="/UF")
	public String getUselessFact() throws IOException {
		String respondedUFact = null;
		DeepL.TextToTranslate textToTranslate;
		String translatedUFact = null;
		
	try {
		respondedUFact = UF.getRandomUFact("https://uselessfacts.jsph.pl/api/v2/facts/random");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	if (respondedUFact.isEmpty()) throw new IOException("Unexpected code $response");
		
	try {
			 textToTranslate = new DeepL.TextToTranslate("PT",new String[]{respondedUFact});
			 translatedUFact = TranslateCenter.Translate(textToTranslate);
			
			 return TranslateCenter.Rights(translatedUFact);
		} catch (Exception e) {
			
			e.printStackTrace();
		};
	
	
	
	
	return null;
	
	}
}
