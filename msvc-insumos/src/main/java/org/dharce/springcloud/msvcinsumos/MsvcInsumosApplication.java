package org.dharce.springcloud.msvcinsumos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcInsumosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcInsumosApplication.class, args);
	}

}
