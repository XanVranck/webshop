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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/getThumbnails")
    public Map<String, List<String>> findAllThumbnails(){
        List<String> allThumbnails = imageService.findAllThumbnails();
        Map<String, List<String>> thumbnails = new HashMap<>();
        thumbnails.put("thumbnails", allThumbnails);
        return thumbnails;
    }

    @PostMapping("/postThumbnails")
    public void storeThumbnail(String filePath){
        Image image = new Image(filePath, ImageType.THUMBNAIL);
        imageService.storeThumbnail(image);
    }
}
