package com.AncaBalcanu.DigitalMusicLibrary.service;

import com.AncaBalcanu.DigitalMusicLibrary.data.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;


    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void delete(Long id){
        songRepository.deleteById(id);
    }

}
