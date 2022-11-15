package com.example.sampleproject.web;

import com.example.sampleproject.model.binding.AlbumAddBindingModel;
import com.example.sampleproject.model.binding.UpdateAlbumBindingModel;
import com.example.sampleproject.model.entities.AlbumEntity;
import com.example.sampleproject.model.response.ResponseMessage;
import com.example.sampleproject.model.service.AlbumServiceModel;
import com.example.sampleproject.service.AlbumService;
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

    public AlbumController(AlbumService recordService) {
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
           return ResponseEntity.badRequest().body(new ResponseMessage("Invalid data!"));
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
        return ResponseEntity.created(location).body(new ResponseMessage("Album added successfully!"));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateAlbum(@RequestBody @Valid UpdateAlbumBindingModel bindingModel, BindingResult br) {

        if (br.hasErrors()) {
           return ResponseEntity.badRequest().body(new ResponseMessage("Invalid data!"));
        }

        AlbumServiceModel albumServiceModel = this.albumService.updateAlbum(bindingModel);

        return ResponseEntity.ok().body(albumServiceModel);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable("id") Long id) {

        this.albumService.deleteAlbum(id);

        return ResponseEntity.ok().body(new ResponseMessage("Album deleted successfully!"));


    }

}
