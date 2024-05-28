package com.geanjesus.acloptrad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AcloptradApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcloptradApplication.class, args);
	}
	
	// DATABASES['default'] =  dj_database_url.config()
	//updated
	//DATABASES = {'default': dj_database_url.config(default='postgres://user:pass@localhost/dbname')}
}
