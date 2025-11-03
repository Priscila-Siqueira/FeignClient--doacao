package com.senac.doacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // Importe este

@SpringBootApplication
@EnableFeignClients // Adicione esta anotação!
public class DoacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoacaoApplication.class, args);
	}
}