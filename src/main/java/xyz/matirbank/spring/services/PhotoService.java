package xyz.matirbank.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.matirbank.spring.models.ReturnContainer;
import xyz.matirbank.spring.models.entities.Photo;
import xyz.matirbank.spring.repositories.PhotoRepository;
import xyz.matirbank.spring.utils.StandardErrors;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public PhotoService() {
    }

    public ReturnContainer<Photo> savePhotoToDatabase(Photo photos) {
        Photo photo = photoRepository.save(photos);
        if (photo != null) {
            return new ReturnContainer<Photo>().returnData(photos);
        } else {
            return new ReturnContainer<Photo>().returnErrorSummary(StandardErrors.E1501_PHOTO_UPLOAD_FAILED);
        }
    }
}
