package com.example.sampleproject.model.service;

import jdk.jfr.Name;

import java.util.List;

public class ArtistServiceModel {

    private String name;
    private String description;

    @Name("vinyls")
    private List<AlbumServiceModel> vinylServiceModels;

    public String getName() {
        return name;
    }

    public ArtistServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<AlbumServiceModel> getVinylServiceModels() {
        return vinylServiceModels;
    }

    public ArtistServiceModel setVinylServiceModels(List<AlbumServiceModel> vinylServiceModels) {
        this.vinylServiceModels = vinylServiceModels;
        return this;
    }
}
