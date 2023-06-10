package com.countryinfo;

import com.countryinfo.model.Country;
import com.countryinfo.repository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@EnableMongoRepositories
@SpringBootApplication
public class CountryInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryInfoApplication.class, args);
	}


	@Bean
	CommandLineRunner init(CountryRepository countryRepository) {
		return args -> {
			try (InputStream inputStream = getClass().getResourceAsStream("/data/countries.json")) {
				ObjectMapper objectMapper = new ObjectMapper();
				TypeReference<List<Country>> typeReference = new TypeReference<>() {};
				List<Country> countries = objectMapper.readValue(inputStream, typeReference);

				countryRepository.deleteAll();
				countryRepository.saveAll(countries);

				log.info("Data has been written to the database.");
			} catch (IOException e) {
				log.error("Unable to save countries", e);
			}
		};
	}
}
