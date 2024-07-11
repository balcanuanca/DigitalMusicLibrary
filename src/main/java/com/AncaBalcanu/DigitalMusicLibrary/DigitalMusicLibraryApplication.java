package com.AncaBalcanu.DigitalMusicLibrary;

import com.AncaBalcanu.DigitalMusicLibrary.data.AlbumRepository;
import com.AncaBalcanu.DigitalMusicLibrary.data.SongRepository;
import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.AncaBalcanu.DigitalMusicLibrary.model.Song;
import com.AncaBalcanu.DigitalMusicLibrary.service.ArtistService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DigitalMusicLibraryApplication {

	private ArtistService artistService;

	private AlbumRepository albumRepository;

	private SongRepository songRepository;

	public DigitalMusicLibraryApplication(ArtistService artistService, AlbumRepository albumRepository, SongRepository songRepository) {
		this.artistService = artistService;
		this.albumRepository = albumRepository;
		this.songRepository = songRepository;
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
				for(Artist artist: artists) {
					var artistId = artistService.save(artist).getId();
					for(Album album: artist.getAlbums()) {
						album.setArtistId(artistId);
						var albumId = albumRepository.save(album).getId();
						for(Song song: album.getSongs()) {
							song.setAlbumId(albumId);
							songRepository.save(song);
						}
					}
				}
				System.out.println("Artists Saved!");
				System.out.println(artists);
			} catch (IOException e){
				System.out.println("Unable to save artists: " + e.getMessage());
			}
		};
	}

}
