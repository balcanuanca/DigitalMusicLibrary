package com.AncaBalcanu.DigitalMusicLibrary.controller;

import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.AncaBalcanu.DigitalMusicLibrary.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping()
    public String showAllArtists(Model model){
        var artistList = artistService.findAll();
        model.addAttribute("artists", artistList);
        return "showAllArtists";
    }

    @GetMapping("/{id}")
    public String  showArtistDetails(@PathVariable Long id,Model model){
        Artist foundArtist = artistService.findById(id).get();
        model.addAttribute("foundArtist", foundArtist);
        return "showArtistDetails";
    }

    @GetMapping("/new")
    public String showAddArtistForm(Model model)
    {
        model.addAttribute("artist", new Artist());
        return "artistForm";
    }
    @PostMapping("/new")
    public ModelAndView addArtist(Artist artist){
        artistService.save(artist);
        return new ModelAndView("redirect:http://localhost:8080/artists");
    }



}
