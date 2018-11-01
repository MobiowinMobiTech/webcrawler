package com.prudentail.crawler.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "domain")
@XmlAccessorType (XmlAccessType.FIELD)
public class Domain {
	
	@XmlElement(name = "subdomain")
	private List<SubDomain> subdomainList = null;

	public List<SubDomain> getCustomerList() {
		return subdomainList;
	}

	public void setCustomerList(List<SubDomain> customerList) {
		this.subdomainList = customerList;
	}
	
	

}
