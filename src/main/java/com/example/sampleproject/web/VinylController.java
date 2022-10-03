package com.example.sampleproject.web;

import com.example.sampleproject.model.service.AlbumServiceModel;
import com.example.sampleproject.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vinyl")
public class VinylController {

    private final ModelMapper mapper;
    private final AlbumService albumService;

    public VinylController(ModelMapper mapper, AlbumService recordService) {
        this.mapper = mapper;
        this.albumService = recordService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVinyls() {
        List<AlbumServiceModel> allVinyls = this.albumService.findAllVinyls();
        System.out.println();
        return ResponseEntity.ok(allVinyls);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AlbumServiceModel> getVinylById(@PathVariable("id") Long id) {
        AlbumServiceModel vinylServiceModel = this.albumService.finById(id);
        return ResponseEntity.ok(vinylServiceModel);
    }

    @GetMapping("/find-by-album/{album}")
    public ResponseEntity<AlbumServiceModel> findVinylByName(@PathVariable("album") String album) {
        AlbumServiceModel serviceModel = this.albumService.findByAlbum(album);
        return ResponseEntity.ok(serviceModel);
    }

}
