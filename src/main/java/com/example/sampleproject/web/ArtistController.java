package com.example.sampleproject.web;

import com.example.sampleproject.model.binding.ArtistAddBindingModel;
import com.example.sampleproject.model.binding.UpdateArtistBindingModel;
import com.example.sampleproject.model.entities.ArtistEntity;
import com.example.sampleproject.model.response.ResponseMessage;
import com.example.sampleproject.model.service.ArtistServiceModel;
import com.example.sampleproject.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<ArtistServiceModel>> getAllArtists() {
        List<ArtistServiceModel> allArtists = this.artistService.findAllArtists();
        return ResponseEntity.ok(allArtists);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ArtistServiceModel> getArtistById(@PathVariable("id") Long id) {
        ArtistServiceModel artistServiceModel = this.artistService.finById(id);
        return ResponseEntity.ok(artistServiceModel);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<ArtistServiceModel> findVinylByName(@PathVariable("name") String name) {
        ArtistServiceModel serviceModel = this.artistService.findByName(name);
        return ResponseEntity.ok(serviceModel);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArtist(@RequestBody @Valid ArtistAddBindingModel addBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
             ResponseEntity.badRequest().body(new ResponseMessage("Invalid data!"));
        }
        if (this.artistService.existsByName(addBindingModel.getName())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Artist with this name already exists!"));
        }
        ArtistEntity artist = this.artistService.addArtist(addBindingModel);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(artist.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateArtist(@RequestBody @Valid UpdateArtistBindingModel updateArtistBindingModel,
                                          BindingResult bindingResult, @PathVariable("id") Long id) {

        if (bindingResult.hasErrors()) {
            ResponseEntity.badRequest().body(new ResponseMessage("Invalid data!"));
        }

        ArtistEntity artistUpdate = this.artistService.updateArtist(updateArtistBindingModel);

        return ResponseEntity.ok().body(artistUpdate);
    }
}
