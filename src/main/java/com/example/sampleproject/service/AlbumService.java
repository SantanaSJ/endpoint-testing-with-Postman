package com.example.sampleproject.service;

import com.example.sampleproject.model.service.AlbumServiceModel;

import java.util.List;

public interface AlbumService {
    List<AlbumServiceModel> findAllVinyls();

    List<AlbumServiceModel> findByArtist(String name);

    List<AlbumServiceModel> findByArtistId(Long id);

    AlbumServiceModel finById(Long id);

    AlbumServiceModel findByAlbum(String name);
}
