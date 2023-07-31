package com.fabu.rpgexample.controller;

import com.fabu.rpgexample.repository.CharacterRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.fabu.rpgexample.model.CharacterModel;

@Controller
public class CharacterController {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping(value={"index", "/"})
    public String getAllCharacters(Model model) {
        model.addAttribute("charactermodel", characterRepository.findAll());
        System.out.println("Inside index method");
        return "characters";
    }

    @GetMapping("/createcharacter")
    public String showCreateForm(Model model) {
        model.addAttribute("charactermodel", new CharacterModel()); // Add an empty CharacterModel object to the model
        System.out.println("Inside createch method");
        return "create";
    }

    @PostMapping("/addcharacter")
    public String addUser(@Valid CharacterModel characterModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/create";
        }

        characterRepository.save(characterModel);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        CharacterModel characterModel = characterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid character Id:" + id));
        model.addAttribute("charactermodel", characterModel);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid CharacterModel characterModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            characterModel.setId(id);
            return "update";
        }
        characterRepository.save(characterModel);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        CharacterModel characterModel = characterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        characterRepository.delete(characterModel);

        return "redirect:/index";
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<CharacterModel> getCharacterById(@PathVariable Long id) {
        CharacterModel character = characterRepository.findById(id).orElse(null);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}
