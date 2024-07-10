package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy="artist", cascade = CascadeType.ALL)
    private List<Album> albums;
}