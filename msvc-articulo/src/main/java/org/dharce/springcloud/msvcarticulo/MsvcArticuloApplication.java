package org.dharce.springcloud.msvcarticulo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcArticuloApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcArticuloApplication.class, args);
	}

}
