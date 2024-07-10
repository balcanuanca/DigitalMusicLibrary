package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue
    private Long id;
    private long albumId;
    private String title;
    private String length;
}
