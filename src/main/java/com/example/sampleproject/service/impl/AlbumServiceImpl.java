package com.example.sampleproject.service.impl;

import com.example.sampleproject.exception.AlbumNotFoundException;
import com.example.sampleproject.exception.ArtistNotFoundException;
//import com.example.sampleproject.exception.VinylNotFoundException;
import com.example.sampleproject.model.binding.AlbumAddBindingModel;
import com.example.sampleproject.model.binding.UpdateAlbumBindingModel;
import com.example.sampleproject.model.entities.AlbumEntity;
import com.example.sampleproject.model.entities.ArtistEntity;
import com.example.sampleproject.model.service.AlbumServiceModel;
import com.example.sampleproject.repository.AlbumRepository;
import com.example.sampleproject.repository.ArtistRepository;
import com.example.sampleproject.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final ModelMapper mapper;
    private final ArtistRepository artistRepository;

    public AlbumServiceImpl(AlbumRepository vinylRepository, ModelMapper mapper, ArtistRepository artistRepository) {
        this.albumRepository = vinylRepository;
        this.mapper = mapper;
        this.artistRepository = artistRepository;
    }


    @Override
    public List<AlbumServiceModel> findAllVinyls() {
        List<AlbumEntity> allVinyls = this.albumRepository.getAllVinyls();
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
        List<AlbumEntity> vinylEntities = this.albumRepository.findByArtist(name);
        return getVinylServiceModels(vinylEntities);


    }

    @Override
    public List<AlbumServiceModel> findByArtistId(Long id) {
        List<AlbumEntity> entities = this.albumRepository.findByArtistId(id);
        return getVinylServiceModels(entities);
    }

    @Override
    public AlbumServiceModel finById(Long id) {
        AlbumServiceModel albumServiceModel = this.albumRepository.findById(id)
                .map(v -> {
                    AlbumServiceModel map = this.mapper.map(v, AlbumServiceModel.class);
                    map.setArtist(v.getArtist().getName());
                    return map;
                })
                .orElseThrow(() -> new AlbumNotFoundException("Vinyl with id" + id + " was not found!"));
        return albumServiceModel;
    }

    @Override
    public AlbumServiceModel findByAlbum(String album) {
        AlbumEntity albumEntity = this.albumRepository
                .findByAlbumName(album)
                .orElseThrow(() -> new AlbumNotFoundException("Album with name " + album + " was not found!"));

        AlbumServiceModel serviceModel = this.mapper.map(albumEntity, AlbumServiceModel.class);
        serviceModel.setArtist(albumEntity.getArtist().getName());
        return serviceModel;
    }

    @Override
    public boolean existsByName(String name) {
        if (this.albumRepository.existsByAlbumName(name)) {
            return true;
        }
        return false;
    }

    @Override
    public AlbumEntity addAlbum(AlbumAddBindingModel addBindingModel) {
        ArtistEntity artist = this.artistRepository
                .findByName(addBindingModel.getArtist())
                .orElseThrow(() -> new ArtistNotFoundException("Artist was not found!"));
        AlbumEntity albumEntity = this.mapper.map(addBindingModel, AlbumEntity.class);
        albumEntity.setArtist(artist);
        return this.albumRepository.save(albumEntity);
    }

    @Override
    public AlbumServiceModel updateAlbum(UpdateAlbumBindingModel bindingModel) {

        AlbumEntity albumEntity = this.albumRepository
                .findById(bindingModel.getId())
                .orElseThrow(() -> new AlbumNotFoundException("Album Entity with id " + bindingModel.getId() + " was not found!"));

        albumEntity
                .setDescription(bindingModel.getDescription());
        if (bindingModel.getAlbumName() != null) {
            albumEntity.setAlbumName(bindingModel.getAlbumName());
        }
        this.albumRepository.save(albumEntity);
        AlbumServiceModel albumServiceModel = this.mapper.map(albumEntity, AlbumServiceModel.class);
        albumServiceModel.setArtist(albumEntity.getArtist().getName());


        return albumServiceModel;
    }

    @Override
    public void deleteAlbum(Long id) {
        AlbumEntity albumEntity = this.albumRepository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album Entity with id " + id + " was not found!"));
        this.albumRepository.deleteById(albumEntity.getId());
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
