package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue
    private Long id;
    private long albumId;
    @NotEmpty(message = "Title can not be empty")
    private String title;
    @NotEmpty(message = "Length can not be empty")
    private String length;
}
