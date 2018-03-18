package com.zhu.classloader;

public class Controller {
	
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}

	private Service service;
	public void insert(){
		service.insert();
	}
}
