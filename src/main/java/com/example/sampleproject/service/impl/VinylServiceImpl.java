package com.example.sampleproject.service.impl;

import com.example.sampleproject.exception.VinylNotFoundException;
import com.example.sampleproject.model.entities.AlbumEntity;
import com.example.sampleproject.model.service.AlbumServiceModel;
import com.example.sampleproject.repository.VinylRepository;
import com.example.sampleproject.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VinylServiceImpl implements AlbumService {
    private final VinylRepository vinylRepository;
    private final ModelMapper mapper;

    public VinylServiceImpl(VinylRepository vinylRepository, ModelMapper mapper) {
        this.vinylRepository = vinylRepository;
        this.mapper = mapper;
    }


    @Override
    public List<AlbumServiceModel> findAllVinyls() {
        List<AlbumEntity> allVinyls = this.vinylRepository.getAllVinyls();
        List<AlbumServiceModel> collect = allVinyls
                .stream()
                .map(a -> {
                    AlbumServiceModel map = this.mapper.map(a, AlbumServiceModel.class);
                     return map.setArtist(a.getArtist().getName());
                })
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<AlbumServiceModel> findByArtist(String name) {
        List<AlbumEntity> vinylEntities = this.vinylRepository.findByArtist(name);
        return getVinylServiceModels(vinylEntities);


    }

    @Override
    public List<AlbumServiceModel> findByArtistId(Long id) {
        List<AlbumEntity> entities = this.vinylRepository.findByArtistId(id);
        return getVinylServiceModels(entities);
    }

    @Override
    public AlbumServiceModel finById(Long id) {
        AlbumServiceModel vinylServiceModel = this.vinylRepository.findById(id)
                .map(v -> {
                    AlbumServiceModel map = this.mapper.map(v, AlbumServiceModel.class);
                    map.setArtist(v.getArtist().getName());
                    return map;
                })
                .orElseThrow(() -> new VinylNotFoundException("Vinyl with id" + id + " was not found!"));
        return vinylServiceModel;
    }

    @Override
    public AlbumServiceModel findByAlbum(String album) {
        AlbumEntity albumEntity = this.vinylRepository
                .findByAlbum(album)
                .orElseThrow(() -> new VinylNotFoundException("Vinyl with name " + album + " was not found!"));

        AlbumServiceModel serviceModel = this.mapper.map(albumEntity, AlbumServiceModel.class);
        serviceModel.setArtist(albumEntity.getArtist().getName());
        return serviceModel;
    }

    private List<AlbumServiceModel> getVinylServiceModels(List<AlbumEntity> entities) {
        List<AlbumServiceModel> collect = entities
                .stream()
                .map(v -> {
                    AlbumServiceModel map = this.mapper.map(v, AlbumServiceModel.class);
                    map.setArtist(v.getArtist().getName());
                    return map;
                })
                .collect(Collectors.toList());
        return collect;
    }
}
