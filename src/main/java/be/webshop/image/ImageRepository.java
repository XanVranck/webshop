package be.webshop.image;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ImageRepository {
    @PersistenceContext
    private EntityManager entityManager;


    List<String> findAllThumbnails() {
        String query = "select i from be.webshop.image.Image i where i.imageType = 'THUMBNAIL'";
        List<Image> imageList = entityManager.createQuery(query, Image.class)
                .getResultList();
        return imageList.stream().map(Image::getFilePath).collect(Collectors.toList());
    }

    void storeThumbnail(Image image) {
        entityManager.persist(image);
    }
}
