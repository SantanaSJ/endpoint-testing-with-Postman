package com.example.sampleproject.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {

    @ManyToOne
    @JsonManagedReference
    private ArtistEntity artist;

    @Column
    private String albumName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    private List<UserEntity> users;


    public String getAlbumName() {
        return albumName;
    }

    public AlbumEntity setAlbumName(String album) {
        this.albumName = album;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public AlbumEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public AlbumEntity setArtist(ArtistEntity artist) {
        this.artist = artist;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
