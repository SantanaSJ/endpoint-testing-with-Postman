package com.example.sampleproject.validator;

import com.example.sampleproject.model.service.AlbumServiceModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AlbumValidator implements ConstraintValidator<ValidateAlbums, List<AlbumServiceModel>> {

    @Override
    public boolean isValid(List<AlbumServiceModel> albums, ConstraintValidatorContext context) {
        return !albums.isEmpty();
    }
}
