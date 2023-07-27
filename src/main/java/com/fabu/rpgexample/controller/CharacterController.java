package com.fabu.rpgexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fabu.rpgexample.model.CharacterModel;
import com.fabu.rpgexample.service.CharacterService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CharacterModel>> getAllCharacters() {
        List<CharacterModel> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterModel> getCharacterById(@PathVariable Long id) {
        CharacterModel character = characterService.getCharacterById(id);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterModel> updateCharacter(@PathVariable Long id, @RequestBody CharacterModel updatedCharacter) {
        CharacterModel character = characterService.updateCharacter(id, updatedCharacter);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        boolean deleted = characterService.deleteCharacter(id);
        if (deleted) {
            System.out.println("Eliminado correctamente");
            return ResponseEntity.noContent().build();
        } else {
            System.out.println("No se ha eliminado");
            return ResponseEntity.notFound().build();
        }
    }


    // Handler method to create a new character
    @PostMapping("/")
    public ResponseEntity<CharacterModel> createCharacter(@RequestParam String name, @RequestParam int level, @RequestParam String characterClass) {
        CharacterModel characterModel = new CharacterModel(name, level, characterClass);
        CharacterModel createdCharacter = characterService.createCharacter(characterModel);
        return ResponseEntity.created(URI.create("/characters/" + createdCharacter.getId())).body(createdCharacter);
    }
}
