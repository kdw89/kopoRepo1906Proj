package edu.polyedu.stylera_0618;

public class ImageString {
    private String imageName;
    private String imageId;

    public ImageString(String imageId, String imageName){
        this.imageId = imageId;
        this.imageName = imageName;
    }
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}