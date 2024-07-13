package com.AncaBalcanu.DigitalMusicLibrary.controller;

import com.AncaBalcanu.DigitalMusicLibrary.model.Album;
import com.AncaBalcanu.DigitalMusicLibrary.model.Artist;
import com.AncaBalcanu.DigitalMusicLibrary.model.SearchParams;
import com.AncaBalcanu.DigitalMusicLibrary.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
        model.addAttribute("searchParams", new SearchParams());
        return "allArtists";
    }

    @GetMapping("/{id}")
    public String  showArtistDetails(@PathVariable Long id,Model model){
        Artist foundArtist = artistService.findById(id).get();
        model.addAttribute("foundArtist", foundArtist);
        var albumList = foundArtist.getAlbums();
        model.addAttribute("albums", albumList);
        model.addAttribute("searchParams", new SearchParams());
        return "artistDetails";
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

    @GetMapping("/{id}/edit")
    public String showEditArtistForm(Model model,@PathVariable Long id)
    {
        Artist selectedArtist = artistService.findById(id).get();
        model.addAttribute("artist",selectedArtist);
        return "artistForm";
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateArtist(Artist artist){
        artistService.save(artist);
        return new ModelAndView("redirect:http://localhost:8080/artists");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteArtist(@PathVariable Long id){
        artistService.delete(id);
        return new ModelAndView("redirect:http://localhost:8080/artists");
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model){
        List<Artist> artistList = artistService.findAllByNameContaining(name);
        model.addAttribute("artists", artistList);
        return "allArtists";
    }

    @PostMapping("/search")
    public ModelAndView searchArtist(@ModelAttribute SearchParams searchParams){
        return new ModelAndView("redirect:http://localhost:8080/artists/search?name=" + searchParams.getInput());

    }

}
