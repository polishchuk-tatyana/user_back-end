package com.user.userapp.service;

import com.user.userapp.domain.Photo;
import com.user.userapp.domain.enums.PhotoSize;
import com.user.userapp.repository.PhotoRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;

/**
 * Service Implementation for managing [Photo].
 */
@Service
@Transactional
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    /**
     * Create photo.
     *
     * @return created photo.
     */
    public Photo saveOriginal(Photo photo){
        return photoRepository.save(photo);
    }

    /**
     * Create user avatar. If @param is null would be created photo by default.
     *
     * @param avatar to create photo for user if that exists
     * @return created photo.
     */
    public Photo createAvatar(Photo avatar) throws IOException {
        if(avatar != null){
            return photoRepository.save(avatar);
        }
        else{
            return createDefaultAvatar();
        }
    }

    /**
     * Create basic information by default of the photo like avatar for the current user.
     *
     * @return created default photo.
     */
    public Photo createDefaultAvatar() {
        Photo avatar = new Photo(
                IOUtils.byteArray(1),
                "image/png",
                PhotoSize.SIZE_ORIGINAL
        );
        return saveOriginal(avatar);
    }
}