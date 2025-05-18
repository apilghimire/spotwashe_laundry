package com.spotwashe_laundry.model;

public class Order {
	private int orderId;
	private int customerID;
	private int serviceType;
	private int weight;
	private String pickUpDate;
	private String dropoffDate;
	private Long finalPrice;
	private String progress;
	public Order(int serviceType, int weight, String pickUpDate, String dropoffDate) {
		this.serviceType = serviceType;
		this.weight = weight;
		this.pickUpDate = pickUpDate;
		this.dropoffDate = dropoffDate;
	}
	
	public Order(int orderId, int serviceType, int weight, String pickUpDate, String dropoffDate, Long finalPrice) {
		super();
		this.orderId = orderId;
		this.serviceType = serviceType;
		this.weight = weight;
		this.pickUpDate = pickUpDate;
		this.dropoffDate = dropoffDate;
		this.finalPrice = finalPrice;
	}

	public Order(int orderId, int customerID, int serviceType, int weight, String pickUpDate, String dropoffDate,
			Long finalPrice) {
		super();
		this.orderId = orderId;
		this.customerID = customerID;
		this.serviceType = serviceType;
		this.weight = weight;
		this.pickUpDate = pickUpDate;
		this.dropoffDate = dropoffDate;
		this.finalPrice = finalPrice;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public String getDropoffDate() {
		return dropoffDate;
	}
	public void setDropoffDate(String dropoffDate) {
		this.dropoffDate = dropoffDate;
	}
	public Long getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(long finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	
}
