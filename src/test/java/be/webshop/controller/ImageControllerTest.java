package be.webshop.controller;

import be.webshop.infrastructure.SpringIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ImageControllerTest extends SpringIntegrationTest {
    @Autowired
    private ImageController imageController;
    private String filePath = "C:/wd/images/thumbnail/iets.jpg";
    private String filePath2 = "C:/wd/images/thumbnail/iets2.jpg";
    private String filePath3 = "C:/wd/images/thumbnail/iets3.jpg";

    @Before
    public void setUp() throws Exception {
        imageController.storeThumbnail(filePath);
        imageController.storeThumbnail(filePath2);
        imageController.storeThumbnail(filePath3);
    }

    @Test
    public void findAllThumbnails() {
        List<String> allImages = imageController.findAllThumbnails();

        assertThat(allImages.size()).isEqualTo(3);
        assertThat(allImages).containsExactlyInAnyOrder(filePath, filePath2, filePath3);
    }
}
