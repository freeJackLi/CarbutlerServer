package com.freelxl.baselibrary.config;

import android.content.Context;

public class GlobalParams {

	public static String user_account;

	public static String customer_id;

	public static String agent_name;

    public static Context context;

	public static void setCurrentCustomerAccount(String customer_id) {
		GlobalParams.customer_id = customer_id;
	}

	public static String getCurrentCustomerAccount() {
		return customer_id;
	}
}
