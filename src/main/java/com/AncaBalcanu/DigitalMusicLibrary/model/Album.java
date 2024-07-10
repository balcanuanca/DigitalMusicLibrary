package com.AncaBalcanu.DigitalMusicLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue
    @Column(name = "albumId")
    private Long id;
    private long artistId;
    private String title;
    @OneToMany
    @JoinColumn(name = "albumId")
    private List<Song> songs;
    @Column(length = 2000)
    private String description;
}
