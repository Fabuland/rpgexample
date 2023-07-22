package com.fabu.rpgexample.controller;

import com.fabu.rpgexample.model.CharacterModel;
import com.fabu.rpgexample.service.CharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    /*// Handler method to handle form submission from "create.html" and redirect
    @PostMapping("/characters/") // Updated path to match the form action
    public String handleCreateFormSubmission(CharacterModel characterModel) {
        characterService.createCharacter(characterModel);
        // Add any additional processing here if needed
        return "redirect:/"; // Redirect to the home page after creating the character
    }*/

}