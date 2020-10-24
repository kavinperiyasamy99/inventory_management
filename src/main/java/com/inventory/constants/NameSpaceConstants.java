package com.inventory.constants;

public class NameSpaceConstants {

	public static final String LOGIN = "/login/V1.0";
	public static final String CREATE_USER = "/create_user/V1.0";
	public static final String RETRIEVE_USERS = "/retrieve_users/V1.0";
	public static final String RETRIEVE_USER = "/retrieve_user/{userId}/V1.0";
	public static final String UPDATE_USER = "/update_user/{userId}/V1.0";
	public static final String DELETE_USER = "/delete_user/{userId}/V1.0";

	public static final String CREATE_DEALER = "/create_dealer/V1.0";
	public static final String RETRIEVE_DEALERS = "/retrieve_dealers/V1.0";
	public static final String RETRIEVE_DEALER = "/retrieve_dealer/{dealerId}/V1.0";
	public static final String UPDATE_DEALER = "/update_dealer/{dealerId}/V1.0";
	public static final String DELETE_DEALER = "/delete_dealer/{dealerId}/V1.0";
	public static final String FILTER_DEALER = "/filter_dealer/V1.0";

	public interface Dealer{
		public static final String ADD_BILL = "/dealer/{dealerId}/add-bill/V1.0";
		public static final String UPDATE_BILL = "/dealer/{dealerId}/update-bill/{billId}/V1.0";
		public static final String DELETE_BILL = "/dealer/{dealerId}/delete-bill/{billId}/V1.0";
		public static final String RETRIEVE_BILLS = "/dealer/{dealerId}/retrieve-bill/V1.0";
		public static final String RETRIEVE_BILL = "/dealer/{dealerId}/retrieve-bill/{billId}/V1.0";

		public static final String ADD_PAYMENT = "/dealer/{dealerId}/add-payment/V1.0";
		public static final String UPDATE_PAYMENT = "/dealer/{dealerId}/update-payment/{paymentId}/V1.0";
		public static final String DELETE_PAYMENT = "/dealer/{dealerId}/delete-payment/{paymentId}/V1.0";
		public static final String RETRIEVE_PAYMENTS = "/dealer/{dealerId}/retrieve-payment/V1.0";
		public static final String RETRIEVE_PAYMENT = "/dealer/{dealerId}/retrieve-payment/{paymentId}/V1.0";
	}

    public static final String HEALTH_CHECK = "/health-check";

}
