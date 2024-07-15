package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue
    @Column(name = "artistId")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @OneToMany //(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "artistId")
    private List<Album> albums;
}