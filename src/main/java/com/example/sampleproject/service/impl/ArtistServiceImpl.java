package com.example.sampleproject.service.impl;

import com.example.sampleproject.exception.ArtistNotFoundException;
import com.example.sampleproject.model.binding.ArtistAddBindingModel;
import com.example.sampleproject.model.binding.UpdateArtistBindingModel;
import com.example.sampleproject.model.entities.AlbumEntity;
import com.example.sampleproject.model.entities.ArtistEntity;
import com.example.sampleproject.model.service.ArtistServiceModel;
import com.example.sampleproject.model.service.AlbumServiceModel;
import com.example.sampleproject.repository.ArtistRepository;
import com.example.sampleproject.service.ArtistService;
import com.example.sampleproject.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ModelMapper mapper;
    private final ArtistRepository artistRepository;
    private final AlbumService albumService;

    public ArtistServiceImpl(ModelMapper mapper, ArtistRepository artistRepository, AlbumService albumService) {
        this.mapper = mapper;
        this.artistRepository = artistRepository;
        this.albumService = albumService;
    }

    @Override
    public List<ArtistServiceModel> findAllArtists() {
        List<ArtistEntity> allArtists = this.artistRepository.getAllArtists();
        List<ArtistServiceModel> collect = allArtists
                .stream()
                .map(a -> {
                    ArtistServiceModel map = this.mapper.map(a, ArtistServiceModel.class);
                    List<AlbumServiceModel> vinylServiceModels = this.albumService.findByArtist(a.getName());
                    map.setAlbumServiceModels(vinylServiceModels);
                    return map;
                })
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public ArtistServiceModel finById(Long id) {
        ArtistServiceModel artistServiceModel = this.artistRepository
                .findById(id)
                .map(a -> {
                    ArtistServiceModel serviceModel = this.mapper.map(a, ArtistServiceModel.class);
                    List<AlbumServiceModel> serviceModels = this.albumService.findByArtistId(id);
                    serviceModel.setAlbumServiceModels(serviceModels);
                    return serviceModel;
                })
                .orElseThrow(() -> new ArtistNotFoundException("Artist Entity with id " + id + " was not found!"));
//                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Artist Entity with id %d was not found!", id)));
        return artistServiceModel;
    }

    @Override
    public ArtistServiceModel findByName(String name) {
        ArtistServiceModel artistServiceModel = this.artistRepository
                .findByName(name)
                .map(a -> {
                    ArtistServiceModel serviceModel = this.mapper.map(a, ArtistServiceModel.class);
                    List<AlbumServiceModel> serviceModels = this.albumService.findByArtist(a.getName());
                    serviceModel.setAlbumServiceModels(serviceModels);
                    return serviceModel;
                })
                .orElseThrow(() -> new ArtistNotFoundException("Artist Entity with name " + name + " was not found!"));
        return artistServiceModel;
    }

    @Override
    public boolean existsByName(String name) {
        return this.artistRepository.existsByName(name);
    }

    @Override
    public ArtistEntity addArtist(ArtistAddBindingModel addBindingModel) {
        List<AlbumServiceModel> modelAlbums = addBindingModel.getAlbums();

        ArtistEntity entity = this.mapper.map(addBindingModel, ArtistEntity.class);
        List<AlbumEntity> albumEntities = modelAlbums
                .stream()
                .map(a -> {
                    AlbumEntity map = this.mapper.map(a, AlbumEntity.class);
                    map.setArtist(entity);
                    return map;
                })
                .collect(Collectors.toList());
        entity.setAlbumEntity(albumEntities);
        ArtistEntity saved = this.artistRepository.save(entity);
//        this.albumService.save

        return saved;
    }

    @Override
    public ArtistEntity updateArtist(UpdateArtistBindingModel updateModel) {

        ArtistEntity artist = this.artistRepository
                .findById(updateModel.getId())
                .orElseThrow(() -> new ArtistNotFoundException("Artist Entity with id " + updateModel.getId() + " was not found!"));

        artist
                .setName(updateModel.getName())
                .setDescription(updateModel.getDescription());

        if (updateModel.getAlbums() != null) {
            artist.setAlbumEntity(updateModel.getAlbums()
                    .stream()
                    .map(a -> this.mapper.map(a, AlbumEntity.class))
                    .collect(Collectors.toList()));
        }

        return this.artistRepository.save(artist);
    }
}
