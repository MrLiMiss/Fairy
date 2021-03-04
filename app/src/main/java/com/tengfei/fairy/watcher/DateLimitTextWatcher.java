package com.tengfei.fairy.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 期限textwatcher
 */
public class DateLimitTextWatcher implements TextWatcher {
    EditText dateLimitEdit;

    public DateLimitTextWatcher(EditText priceEdit) {
        this.dateLimitEdit = priceEdit;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {
        if (dateLimitEdit.getText().toString().matches("^0")) {//判断当前的输入第一个数是不是为0
            dateLimitEdit.setText("");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
