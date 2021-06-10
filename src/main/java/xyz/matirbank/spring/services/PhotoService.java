package xyz.matirbank.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.models.entities.Photos;
import xyz.matirbank.spring.repositories.PhotoRepository;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;
    
    public PhotoService() {}
    
    public Photos savePhotoToDatabase(Photos photos) {
        return photoRepository.save(photos);
    }
}
