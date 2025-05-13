package com.spotwashe_laundry.model;

public class Service {
	private int serviceId;
	private String serivceName;
	private int servicePrice;
	
	public Service() {

	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serivceName;
	}

	public void setServiceName(String serivceName) {
		this.serivceName = serivceName;
	}

	public int getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(int servicePrice) {
		this.servicePrice = servicePrice;
	}
	
}
