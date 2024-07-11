package com.AncaBalcanu.DigitalMusicLibrary.data;

import com.AncaBalcanu.DigitalMusicLibrary.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findAllByAlbumId(Long id);
}
