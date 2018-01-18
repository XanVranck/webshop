package be.webshop.controller;

import be.webshop.image.Image;
import be.webshop.image.ImageService;
import be.webshop.image.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping(name = "/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<String> findAllThumbnails(){
        return imageService.findAllThumbnails();
    }

    @PostMapping
    public void storeThumbnail(String filePath){
        Image image = new Image(filePath, ImageType.THUMBNAIL);
        imageService.storeThumbnail(image);
    }
}
