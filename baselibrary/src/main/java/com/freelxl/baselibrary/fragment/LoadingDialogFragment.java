package com.freelxl.baselibrary.fragment;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.volley.toolbox.StringRequest;
import com.freelxl.baselibrary.R;

public class LoadingDialogFragment extends DialogFragment {
	public static final String LOADING_TAG = "loading_tag";
	public static LoadingDialogFragment dialog;
	StringRequest stringRequest;
	DialogInterface dialogInterface;

	public LoadingDialogFragment(StringRequest stringRequest) {
		super();
		this.stringRequest = stringRequest;
	}

	public LoadingDialogFragment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().setCanceledOnTouchOutside(false);
		getDialog().getWindow().setBackgroundDrawable(
				new ColorDrawable(android.R.color.transparent));
		ViewGroup view = (ViewGroup) View.inflate(getActivity(), R.layout.dialog_loading, null);
		return view;
	}

	public static void showDialog(FragmentActivity activity) {
		if (dialog == null) {
			dialog = new LoadingDialogFragment();
			dialog.show(activity.getSupportFragmentManager().beginTransaction(), LOADING_TAG);
		}
	}

	public static void showDialog(FragmentActivity activity, StringRequest stringRequest) {
		if (dialog == null) {
			dialog = new LoadingDialogFragment(stringRequest);
			dialog.show(activity.getSupportFragmentManager().beginTransaction(), LOADING_TAG);
		}
	}

	public static void showDialog(FragmentActivity activity, DialogInterface dialogInterface) {
		if (dialog == null) {
			dialog = new LoadingDialogFragment();
			dialog.show(activity.getSupportFragmentManager().beginTransaction(), LOADING_TAG);
			dialog.dialogInterface = dialogInterface;
		}
	}

	public static void myDismiss() {
		if (dialog != null && dialog.isResumed()) {
			dialog.dismiss();
			dialog = null;
		}
	}

	public static boolean isDialogResumed() {
		if (dialog != null && dialog.isResumed()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		// if (stringRequest != null && !stringRequest.isCanceled()) {
		// stringRequest.cancel();
		// }
		if (LoadingDialogFragment.dialog != null
				&& LoadingDialogFragment.dialog.dialogInterface != null) {
			LoadingDialogFragment.dialog.dialogInterface.cancel();
		}
		LoadingDialogFragment.dialog = null;
	}

	@Override
	public void onPause() {
		super.onPause();
		LoadingDialogFragment.dialog = null;
	}

}
