package com.fabu.rpgexample.controller;

import com.fabu.rpgexample.model.CharacterModel;
import com.fabu.rpgexample.service.CharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    private final CharacterService characterService;

    public WebController(CharacterService characterService) {
        this.characterService = characterService;
    }

    // Handler method for the "Home" page

    @GetMapping("/")
    public String getCharacterList(Model model) {
        model.addAttribute("characters", characterService.getAllCharacters());
        return "characters";
    }

    // Handler method to show the "create.html" form
    @GetMapping("/create")
    public String showCreateForm() {
        return "create";
    }

    @DeleteMapping("/characters/delete/{id}")
    public String deleteCharacterWeb(@PathVariable Long id) {
        // Delete character using WebClient
        // (Add your WebClient logic here)
        return "redirect:/"; // Redirect to the home page after successful deletion
    }
}