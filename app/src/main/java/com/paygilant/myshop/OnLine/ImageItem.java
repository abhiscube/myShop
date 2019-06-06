package com.paygilant.myshop.OnLine;

public class ImageItem {
    private int image;
    private String title;
    private CategoryJewel jewel;


    private String price;


    public ImageItem(int image, String title,String price, CategoryJewel jewel) {
        super();
        this.price = price;
        this.image = image;
        this.title = title;
        this.jewel = jewel;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CategoryJewel getJewel() {
        return jewel;
    }

    public void setJewel(CategoryJewel jewel) {
        this.jewel = jewel;
    }

    @Override
    public String toString() {
        return "ImageItem{" +
                "image=" + image +
                ", title='" + title + '\'' +
                ", jewel=" + jewel.toString() +
                ", price='" + price + '\'' +
                '}';
    }
}