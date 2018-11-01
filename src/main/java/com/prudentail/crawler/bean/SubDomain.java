package com.prudentail.crawler.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubDomain {

	private String name;

	private List<ImageInfo> imageInfo;

	public SubDomain() {
		super();
	}

	public SubDomain(String name, List<ImageInfo> imageInfo) {
		super();
		this.name = name;
		this.imageInfo = imageInfo;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public List<ImageInfo> getImageInfo() {
		return imageInfo;
	}

	@XmlElement
	public void setImageInfo(List<ImageInfo> imageInfo) {
		this.imageInfo = imageInfo;
	}

}
