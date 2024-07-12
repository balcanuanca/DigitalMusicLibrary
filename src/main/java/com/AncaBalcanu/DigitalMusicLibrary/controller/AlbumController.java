package com.AncaBalcanu.DigitalMusicLibrary.controller;

import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.AncaBalcanu.DigitalMusicLibrary.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public String  showAlbumDetails(@PathVariable Long id,Model model){
        Album foundAlbum = albumService.findById(id).get();
        model.addAttribute("foundAlbum", foundAlbum);
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

}
