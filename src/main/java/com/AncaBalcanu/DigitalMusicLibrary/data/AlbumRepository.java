package com.AncaBalcanu.DigitalMusicLibrary.data;

import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    List<Album> findAllByArtistId(Long id);
}
