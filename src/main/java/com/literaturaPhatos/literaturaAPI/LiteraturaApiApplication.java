package com.literaturaPhatos.literaturaAPI;

import com.literaturaPhatos.literaturaAPI.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApiApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.app();
	}
}
