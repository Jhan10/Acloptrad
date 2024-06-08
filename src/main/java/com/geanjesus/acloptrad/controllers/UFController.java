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
	
	@GetMapping
	@RequestMapping(value="/UfPage")
	public String getUselessFactPage() {
		 String initPage = 
				 "<!DOCTYPE html>\r\n"
				 + "<html lang=\"en\">\r\n"
				 + "<head>\r\n"
				 + "    <meta charset=\"UTF-8\">\r\n"
				 + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				 + "    <title>Emptyy</title>\r\n"
				 + "    <script src=\"https://unpkg.com/typeit@8.7.1/dist/index.umd.js\" defer></script>\r\n"
				 + "    <script defer>\r\n"
				 + "        document.addEventListener('DOMContentLoaded', ()=>{\r\n"
				 + "    new TypeIt(\".animatedText\",{\r\n"
				 + "        speed: 50,\r\n"
				 + "        loop: true\r\n"
				 + "    }).go()\r\n"
				 + "});\r\n"
				 + "    </script>"
				 +"<link href=\"https://fonts.cdnfonts.com/css/cumiskat\" rel=\"stylesheet\">"
				 + "</head>\r\n"
				 + "<body>\r\n"
				 +"<span class=\"animatedText\" style=\"font-family: 'cumiskat', sans-serif;\">";
		 String entirePage=null
				 ,respondedUFact=null
				 ,translatedUFact = null;
		DeepL.TextToTranslate textToTranslate;
		
		 try {
			 
			 respondedUFact = UF.getRandomUFact("https://uselessfacts.jsph.pl/api/v2/facts/random");		 
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		 try {
			 textToTranslate = new DeepL.TextToTranslate("PT",new String[]{respondedUFact});
			 translatedUFact = TranslateCenter.Translate(textToTranslate);
		} catch (Exception e) {
			
			e.printStackTrace();
		};

		entirePage=initPage+translatedUFact+"</span>";
		entirePage = TranslateCenter.Rights(entirePage);
		
		String lastPartPage = "  </body>\r\n"
				 + "</html>\r\n";
				 
				 return entirePage+lastPartPage;
	}
}
