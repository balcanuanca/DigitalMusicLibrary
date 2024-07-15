package com.AncaBalcanu.DigitalMusicLibrary.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SongInterm {
    @Id
    @GeneratedValue
    private Long id;
    private long albumId;
    @NotEmpty(message = "Title can not be empty")
    private String title;
    @NotNull(message = "Length can not be empty")
    @Positive
    @Max(59)
    private int minutes;
    @NotNull(message = "Length can not be empty")
    @Positive
    @Max(59)
    private int seconds;
}
