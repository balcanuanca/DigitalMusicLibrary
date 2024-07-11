package com.AncaBalcanu.DigitalMusicLibrary.service;

import com.AncaBalcanu.DigitalMusicLibrary.data.AlbumRepository;
import com.AncaBalcanu.DigitalMusicLibrary.data.SongRepository;
import com.AncaBalcanu.DigitalMusicLibrary.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;
    private SongRepository songRepository;

    public AlbumService(AlbumRepository albumRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public void delete(Long id){
        List<Song> songs= songRepository.findAllByAlbumId(id);
        for(Song song : songs) {
            songRepository.delete(song);
        }
        albumRepository.deleteById(id);
    }

}
