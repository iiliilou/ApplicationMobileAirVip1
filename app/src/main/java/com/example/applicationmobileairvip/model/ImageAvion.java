package com.example.applicationmobileairvip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageAvion {
    @JsonProperty("image_id")
    private int imageId;

    @JsonProperty("url")
    private String url;

    @JsonProperty("orderIndex")
    private int orderIndex;

    // Getters et setters

    public int getImageId() { return imageId; }
    public void setImageId(int imageId) { this.imageId = imageId; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public int getOrderIndex() { return orderIndex; }
    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }
}
