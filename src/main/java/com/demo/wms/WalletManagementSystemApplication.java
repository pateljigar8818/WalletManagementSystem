package com.demo.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WalletManagementSystemApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WalletManagementSystemApplication.class);
    }	

	public static void main(String[] args) {
		SpringApplication.run(WalletManagementSystemApplication.class, args);
	}
}
