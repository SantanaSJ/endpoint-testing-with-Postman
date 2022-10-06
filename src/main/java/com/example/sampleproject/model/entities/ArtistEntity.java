package com.example.sampleproject.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Column
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AlbumEntity> albumEntity;

    public List<AlbumEntity> getAlbumEntity() {
        return albumEntity;
    }

    public ArtistEntity setAlbumEntity(List<AlbumEntity> albumEntity) {
        this.albumEntity = albumEntity;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistEntity setDescription(String description) {
        this.description = description;
        return this;
    }


}
