package hu.unideb.inf.flashcards;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlashcardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashcardsApplication.class, args);
	}
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
