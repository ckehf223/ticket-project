package project.service;

import java.io.Serializable;

public class Service implements Serializable{
	private String category;
	private String serviceHead;
	private String contend;
	
	public Service() {
		
	}
	
	public Service(String category,String serviceHead, String contend) {
		this.category = category;
		this.serviceHead = serviceHead;
		this.contend = contend;
	}

	public String getServiceHead() {
		return serviceHead;
	}

	public void setServiceHead(String serviceHead) {
		this.serviceHead = serviceHead;
	}

	public String getContend() {
		return contend;
	}

	public void setContend(String contend) {
		this.contend = contend;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	
	
}
