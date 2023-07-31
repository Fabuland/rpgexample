package com.fabu.rpgexample.model;

import jakarta.persistence.*;


@Entity
@Table(name = "charactermodel")
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "level")
    private int level;
    @Column(nullable = false, name = "characterclass")
    private String characterClass;

    // Constructors
    public CharacterModel() {
    }

    public CharacterModel(String name, int level, String characterClass) {
        this.name = name;
        this.level = level;
        this.characterClass = characterClass;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    // toString() method for easy logging
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", characterClass='" + characterClass + '\'' +
                '}';
    }
}
