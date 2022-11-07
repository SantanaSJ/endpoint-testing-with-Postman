package com.example.sampleproject.web;

import com.example.sampleproject.model.binding.AlbumAddBindingModel;
import com.example.sampleproject.model.binding.ArtistAddBindingModel;
import com.example.sampleproject.model.binding.UpdateAlbumBindingModel;
import com.example.sampleproject.model.entities.AlbumEntity;
import com.example.sampleproject.model.entities.ArtistEntity;
import com.example.sampleproject.model.response.ResponseMessage;
import com.example.sampleproject.model.service.AlbumServiceModel;
import com.example.sampleproject.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(ModelMapper mapper, AlbumService recordService) {
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

    @PostMapping("/add")
    public ResponseEntity<?> addAlbum(@RequestBody @Valid AlbumAddBindingModel addBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ResponseEntity.badRequest().body(new ResponseMessage("Invalid data!"));
        }
        if (this.albumService.existsByName(addBindingModel.getAlbumName())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Album with this name already exists!"));
        }
        AlbumEntity albumEntity = this.albumService.addAlbum(addBindingModel);
        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/vinyl/{id}")
                .buildAndExpand(albumEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateAlbum(@RequestBody UpdateAlbumBindingModel bindingModel, BindingResult br,
                                         @PathVariable("id") Long id) {

        if (br.hasErrors()) {
            ResponseEntity.badRequest().body(new ResponseMessage("Invalid data!"));
        }

        AlbumServiceModel albumServiceModel = this.albumService.updateAlbum(bindingModel);

        return ResponseEntity.ok().body(albumServiceModel);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable("id") Long id) {

        this.albumService.deleteArtist(id);

        return ResponseEntity.ok().body(new ResponseMessage("Artist deleted successfully!"));


    }

}
