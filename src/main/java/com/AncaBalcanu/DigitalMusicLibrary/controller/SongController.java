package com.AncaBalcanu.DigitalMusicLibrary.controller;

import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.SearchParams;
import com.AncaBalcanu.DigitalMusicLibrary.model.Song;
import com.AncaBalcanu.DigitalMusicLibrary.service.AlbumService;
import com.AncaBalcanu.DigitalMusicLibrary.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private SongService songService;
    private AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
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

    @GetMapping("/{albumId}/search")
    public String search(@RequestParam String title, Model model, @PathVariable Long albumId){
        List<Song> songList = songService.findAllByAlbumIdAndTitleContaining(albumId,title);
        model.addAttribute("songs", songList);
        model.addAttribute("foundAlbum", albumService.findById(albumId).get());
        return "albumDetails";
    }

    @PostMapping("/{albumId}/search")
    public ModelAndView searchAlbum(@ModelAttribute SearchParams searchParams){
        return new ModelAndView("redirect:http://localhost:8080/songs/{albumId}/search?title=" + searchParams.getInput());
    }
}
