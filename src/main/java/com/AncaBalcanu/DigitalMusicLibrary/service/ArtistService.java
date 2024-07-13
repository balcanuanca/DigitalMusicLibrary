package com.AncaBalcanu.DigitalMusicLibrary.service;

import com.AncaBalcanu.DigitalMusicLibrary.data.AlbumRepository;
import com.AncaBalcanu.DigitalMusicLibrary.data.ArtistRepository;
import com.AncaBalcanu.DigitalMusicLibrary.data.SongRepository;
import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private ArtistRepository artistRepository;

    private AlbumRepository albumRepository;
    private AlbumService albumService;

    public ArtistService(ArtistRepository artistRepository, AlbumRepository albumRepository, AlbumService albumService) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.albumService = albumService;
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

    public List<Artist> findAllByNameContaining(String name){
        return artistRepository.findByNameContaining(name);
    }

    public List<Artist> findAll(){
        return artistRepository.findAll();
    }

    public void delete(Long id){
        List<Album> albums = albumRepository.findAllByArtistId(id);
        for(Album album : albums){
            albumService.delete(album.getId());
        }
        artistRepository.deleteById(id);
    }

}
