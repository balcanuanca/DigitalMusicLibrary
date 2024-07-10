package com.AncaBalcanu.DigitalMusicLibrary;

import com.AncaBalcanu.DigitalMusicLibrary.data.ArtistRepository;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.AncaBalcanu.DigitalMusicLibrary.service.ArtistService;
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

	private ArtistService artistService;

	public DigitalMusicLibraryApplication(ArtistService artistService) {
		this.artistService = artistService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DigitalMusicLibraryApplication.class, args);
	}

	// Read json and write to DB
	//@Bean
	CommandLineRunner runner() {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Artist>> typeReference = new TypeReference<List<Artist>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/static/data.json");
			try {
				List<Artist> artists = mapper.readValue(inputStream,typeReference);
				artistService.saveAll(artists);
				System.out.println("Artists Saved!");
				System.out.println(artists);
			} catch (IOException e){
				System.out.println("Unable to save artists: " + e.getMessage());
			}
		};
	}

}
