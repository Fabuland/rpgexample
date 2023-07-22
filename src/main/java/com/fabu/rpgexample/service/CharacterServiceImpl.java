package com.fabu.rpgexample.service;

import com.fabu.rpgexample.model.CharacterModel;
import com.fabu.rpgexample.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<CharacterModel> getAllCharacters() {
        return (List<CharacterModel>) characterRepository.findAll();
    }

    @Override
    public CharacterModel getCharacterById(Long id) {
        return characterRepository.findById(id).orElse(null);
    }

    @Override
    public CharacterModel createCharacter(CharacterModel character) {
        return characterRepository.save(character);
    }

    @Override
    public CharacterModel updateCharacter(Long id, CharacterModel updatedCharacter) {
        CharacterModel existingCharacter = characterRepository.findById(id).orElse(null);
        if (existingCharacter != null) {
            if (updatedCharacter.getName() != null) {
                existingCharacter.setName(updatedCharacter.getName());
            }
            if (updatedCharacter.getLevel() != 0) {
                existingCharacter.setLevel(updatedCharacter.getLevel());
            }
            if (updatedCharacter.getCharacterClass() != null) {
                existingCharacter.setCharacterClass(updatedCharacter.getCharacterClass());
            }
            return characterRepository.save(existingCharacter);
        }
        return null; // Character with given ID not found
    }

    @Override
    public boolean deleteCharacter(Long id) {
        if (characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
            return true;
        }
        return false; // Character with given ID not found
    }
}