package br.com.thiagopaz.dailyselfie.entity;

import java.util.Date;

/**
 * Created by thiago on 23/11/14.
 */
public class Selfie {

    private String imagePath;
    private String thumbPath;
    private Date dateImage;

    public Selfie(String imagePath,String thumbPath,Date dateImage) {
        this.imagePath = imagePath;
        this.thumbPath = thumbPath;
        this.dateImage = dateImage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public Date getDateImage() {
        return dateImage;
    }

    public void setDateImage(Date dateImage) {
        this.dateImage = dateImage;
    }
}
