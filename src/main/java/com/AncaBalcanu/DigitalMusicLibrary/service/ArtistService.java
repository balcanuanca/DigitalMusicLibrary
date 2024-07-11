package com.AncaBalcanu.DigitalMusicLibrary.service;

import com.AncaBalcanu.DigitalMusicLibrary.data.ArtistRepository;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist save (Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> saveAll (List<Artist> artists){
        return artistRepository.saveAll(artists);
    }

    public Optional<Artist> findById(Long id){
        return artistRepository.findById(id);
    }

    public List<Artist> findAll(){
        return artistRepository.findAll();
    }
}
