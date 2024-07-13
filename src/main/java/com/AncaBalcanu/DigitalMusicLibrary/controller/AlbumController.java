package com.AncaBalcanu.DigitalMusicLibrary.controller;

import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.AncaBalcanu.DigitalMusicLibrary.model.SearchParams;
import com.AncaBalcanu.DigitalMusicLibrary.service.AlbumService;
import com.AncaBalcanu.DigitalMusicLibrary.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService albumService;

    private ArtistService artistService;

    public AlbumController(AlbumService albumService, ArtistService artistService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public String  showAlbumDetails(@PathVariable Long id,Model model){
        Album foundAlbum = albumService.findById(id).get();
        model.addAttribute("foundAlbum", foundAlbum);
        var songList = foundAlbum.getSongs();
        model.addAttribute("songs", songList);
        model.addAttribute("searchParams", new SearchParams());;
        return "albumDetails";
    }

    @GetMapping("/{artistId}/new")
    public String showAddAlbumForm(Model model, @PathVariable Long artistId)
    {
        Album newAlbum = new Album();
        newAlbum.setArtistId(artistId);
        model.addAttribute("album", newAlbum);
        return "albumForm";
    }

    @PostMapping("/{artistId}/new")
    public ModelAndView addAlbum(Album album){
        albumService.save(album);
        return new ModelAndView("redirect:http://localhost:8080/artists/{artistId}");
    }

    @GetMapping("/{id}/edit")
    public String showEditAlbumForm(Model model,@PathVariable Long id)
    {
        Album selectedAlbum = albumService.findById(id).get();
        model.addAttribute("album",selectedAlbum);
        return "albumForm";
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateAlbum(Album album){
        var artistId = album.getArtistId();
        albumService.save(album);
        return new ModelAndView("redirect:http://localhost:8080/artists/" + artistId);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteAlbum(@PathVariable Long id){
        var album =  albumService.findById(id).get();
        var artistId = album.getArtistId();
        albumService.delete(id);
        return new ModelAndView("redirect:http://localhost:8080/artists/" + artistId);
    }

    @GetMapping("/{artistId}/search")
    public String search(@RequestParam String title, Model model, @PathVariable Long artistId){
        List<Album> albumList = albumService.findAllByArtistIdAndTitleContaining(artistId,title);
        model.addAttribute("albums", albumList);
        model.addAttribute("foundArtist", artistService.findById(artistId).get());
        return "artistDetails";
    }

    @PostMapping("/{artistId}/search")
    public ModelAndView searchArtist(@ModelAttribute SearchParams searchParams){
        return new ModelAndView("redirect:http://localhost:8080/albums/{artistId}/search?title=" + searchParams.getInput());
    }

}
