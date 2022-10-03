package com.example.sampleproject.repository;

import com.example.sampleproject.model.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    @Query(value = "SELECT * FROM sample_app.artists", nativeQuery = true)
    List<ArtistEntity> getAllArtists();

    Optional<ArtistEntity> findByName(String name);

    boolean existsByName(String name);
}
