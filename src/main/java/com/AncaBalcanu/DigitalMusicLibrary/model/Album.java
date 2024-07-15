package com.AncaBalcanu.DigitalMusicLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Title can not be empty")
    private String title;
    @OneToMany //(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "albumId")
    private List<Song> songs;
    @Column(length = 2000)
    @NotEmpty(message = "Description can not be empty")
    private String description;
}
