package com.fabu.rpgexample.service;

import com.fabu.rpgexample.model.CharacterModel;

import java.util.List;

public interface CharacterService {
    List<CharacterModel> getAllCharacters();
    CharacterModel getCharacterById(Long id);
    CharacterModel createCharacter(CharacterModel character);
    CharacterModel updateCharacter(Long id, CharacterModel updatedCharacter);
    boolean deleteCharacter(Long id);
}