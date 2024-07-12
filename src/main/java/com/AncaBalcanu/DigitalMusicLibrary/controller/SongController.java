package com.AncaBalcanu.DigitalMusicLibrary.controller;

import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Song;
import com.AncaBalcanu.DigitalMusicLibrary.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/songs")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{albumId}/new")
    public String showAddSongForm(Model model, @PathVariable Long albumId)
    {
        Song newSong = new Song();
        newSong.setAlbumId(albumId);
        model.addAttribute("song", newSong);
        return "songForm";
    }

    @PostMapping("/{albumId}/new")
    public ModelAndView addSong(Song song){
        songService.save(song);
        return new ModelAndView("redirect:http://localhost:8080/albums/{albumId}");
    }

    @GetMapping("/{id}/edit")
    public String showEditSongForm(Model model,@PathVariable Long id)
    {
        Song selectedSong = songService.findById(id).get();
        model.addAttribute("song",selectedSong);
        return "songForm";
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateSong(Song song){
        var albumId = song.getAlbumId();
        songService.save(song);
        return new ModelAndView("redirect:http://localhost:8080/albums/" + albumId);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteSong(@PathVariable Long id){
        var song =  songService.findById(id).get();
        var albumId = song.getAlbumId();
        songService.delete(id);
        return new ModelAndView("redirect:http://localhost:8080/albums/" + albumId);
    }
}
