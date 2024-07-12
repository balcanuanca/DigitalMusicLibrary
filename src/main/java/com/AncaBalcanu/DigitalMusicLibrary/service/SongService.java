package com.AncaBalcanu.DigitalMusicLibrary.service;

import com.AncaBalcanu.DigitalMusicLibrary.data.SongRepository;
import com.AncaBalcanu.DigitalMusicLibrary.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song save(Song song){
       return songRepository.save(song);
    }

    public List<Song> saveAll(List<Song> songs)  {
        return songRepository.saveAll(songs);
    }

    public Optional<Song> findById(Long id){
        return songRepository.findById(id);
    }

    public List<Song> findAll(){
        return songRepository.findAll();
    }
    public void delete(Long id){
        songRepository.deleteById(id);
    }

}
