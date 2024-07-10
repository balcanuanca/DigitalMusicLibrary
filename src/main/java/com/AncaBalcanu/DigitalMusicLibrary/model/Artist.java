package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy="artist")
    private List<Album> albums;
}