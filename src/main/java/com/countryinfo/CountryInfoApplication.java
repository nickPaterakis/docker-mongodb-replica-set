package com.countryinfo;

import com.countryinfo.model.Country;
import com.countryinfo.repository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@EnableMongoRepositories
@SpringBootApplication
public class CountryInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryInfoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CountryRepository countryRepository) {
		return args -> {
			ObjectMapper objectMapper = new ObjectMapper();
			TypeReference<List<Country>> typeReference = new TypeReference<>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/countries.json");

			try {
				countryRepository.deleteAll();
				List<Country> countries = objectMapper.readValue(inputStream, typeReference);
				countryRepository.saveAll(countries);
			} catch (IOException e) {
				System.out.println("Unable to save countries" + e.getMessage());
			}
		};
	}
}
