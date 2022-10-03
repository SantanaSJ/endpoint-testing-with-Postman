package com.example.sampleproject.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vinyls")
public class AlbumEntity extends BaseEntity {

    @ManyToOne
    private ArtistEntity artist;

    @Column
    private String album;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    private List<UserEntity> users;


    public String getAlbum() {
        return album;
    }

    public AlbumEntity setAlbum(String album) {
        this.album = album;
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
