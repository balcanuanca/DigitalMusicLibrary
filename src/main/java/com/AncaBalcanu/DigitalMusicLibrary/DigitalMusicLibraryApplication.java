package com.AncaBalcanu.DigitalMusicLibrary;

import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class DigitalMusicLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalMusicLibraryApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Artist>> typeReference = new TypeReference<List<Artist>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/static/data.json");
			try {
				List<Artist> artists = mapper.readValue(inputStream,typeReference);
//				userService.save(users);
				System.out.println("Artists Saved!");
				System.out.println(artists);
			} catch (IOException e){
				System.out.println("Unable to save artists: " + e.getMessage());
			}
		};
	}

}
