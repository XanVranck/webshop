package be.webshop.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<String> findAllThumbnails(){
        return imageRepository.findAllThumbnails();
    }

    public void storeThumbnail(Image image){
        imageRepository.storeThumbnail(image);
    }

}
