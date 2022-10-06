package com.example.sampleproject.model.service;


public class AlbumServiceModel {

    private String artist;
    private String albumName;
    private String description;


    public String getDescription() {
        return description;
    }

    public AlbumServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAlbumName() {
        return albumName;
    }

    public AlbumServiceModel setAlbumName(String albumName) {
        this.albumName = albumName;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumServiceModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }
}
