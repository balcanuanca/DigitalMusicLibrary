package com.AncaBalcanu.DigitalMusicLibrary.data;

import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
}