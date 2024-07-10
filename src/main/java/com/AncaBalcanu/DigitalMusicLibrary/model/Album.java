package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue
    private Long id;
 //   @Embedded
    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;
    private String title;
    @OneToMany(mappedBy="album", cascade = CascadeType.ALL)
    private List<Song> songs;
    @Column(length = 2000)
    private String description;
}
