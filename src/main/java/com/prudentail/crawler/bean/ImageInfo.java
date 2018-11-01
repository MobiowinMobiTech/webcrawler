package com.prudentail.crawler.bean;

public class ImageInfo {

	private String imageUrl;
	private String height;
	private String width;
	public ImageInfo(String imageUrl, String height, String width) {
		super();
		this.imageUrl = imageUrl;
		this.height = height;
		this.width = width;
	}
	public ImageInfo() {
		super();
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	
	
	
	
}
