package com.AncaBalcanu.DigitalMusicLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue
    @Column(name = "artistId")
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "artistId")
    private List<Album> albums;
}