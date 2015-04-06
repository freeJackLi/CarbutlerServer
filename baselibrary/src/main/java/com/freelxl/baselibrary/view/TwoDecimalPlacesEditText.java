package com.freelxl.baselibrary.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;


/**
 * 两位小数的edittext
 * 
 * @author shine
 * 
 */
public class TwoDecimalPlacesEditText extends EditText {
	private boolean notEmpty;
	private OnEmptyStateChangeListener listener;

	public TwoDecimalPlacesEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TwoDecimalPlacesEditText(Context context) {
		super(context);
		init();
	}

	private void init() {
		setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					String value = getText().toString();
					if (value.endsWith(".")) {
						value = value.replaceAll("\\.", "");
						setText(value);
					}
				}
			}
		});
		addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String value = s.toString();
				setEmpty(value.length() == 0);
				if (value.contains(".")) {
					if (value.length() - value.indexOf(".") > 3) {
						value = value.substring(0, value.indexOf(".") + 3);
						setText(value);
						setSelection(value.length());
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

	}

	private void setEmpty(boolean empty) {
		notEmpty = !empty;
		if (listener != null) {
			if (empty) {
				listener.onEmpty();
			} else {
				listener.onNotEmpty();
			}
		}
	}

	public boolean isEmpty() {
		return !notEmpty;
	}

	public void setOnEmptyStateChangeListener(
			OnEmptyStateChangeListener listener) {
		this.listener = listener;
	}

	public interface OnEmptyStateChangeListener {
		void onEmpty();

		void onNotEmpty();
	}

	public float getFloatValue() {
		String value = getText().toString();
		if (TextUtils.isEmpty(value)) {
			return 0;
		} else if (value.matches("^\\d+\\.?\\d*$")) {
			return Float.valueOf(value);
		} else {
			return 0;
		}
	}

}
