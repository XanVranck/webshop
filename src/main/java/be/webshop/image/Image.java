package be.webshop.image;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String filePath;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    protected  Image(){}

    public Image(String filePath, ImageType imageType) {
        this.filePath = filePath;
        this.imageType = imageType;
    }

    public String getFilePath() {
        return filePath;
    }
}
