package com.AncaBalcanu.DigitalMusicLibrary.service;

import com.AncaBalcanu.DigitalMusicLibrary.data.AlbumRepository;
import com.AncaBalcanu.DigitalMusicLibrary.data.SongRepository;
import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.AncaBalcanu.DigitalMusicLibrary.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;
    private SongRepository songRepository;

    public AlbumService(AlbumRepository albumRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public Album save(Album album){
        return albumRepository.save(album);
    }

    public List<Album> saveAll(List<Album> albums){
        return albumRepository.saveAll(albums);
    }

    public Optional<Album> findById(Long id){
        return albumRepository.findById(id);
    }
    public List<Album> findAllByArtistIdAndTitleContaining(Long artistId, String title){
        return albumRepository.findAllByArtistIdAndTitleContaining(artistId,title);
    }

    public List<Album> findAll(){
        return albumRepository.findAll();
    }

    public void delete(Long id){
        List<Song> songs= songRepository.findAllByAlbumId(id);
        for(Song song : songs) {
            songRepository.delete(song);
        }
        albumRepository.deleteById(id);
    }

}
