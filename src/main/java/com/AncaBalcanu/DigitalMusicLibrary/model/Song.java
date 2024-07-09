package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;
    private String title;
    private String length;
}
