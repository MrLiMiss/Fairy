package com.tengfei.fairy.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 金额textwatcher
 */
public class AmountTextWatcher implements TextWatcher {
    EditText priceEdit;

    public AmountTextWatcher(EditText priceEdit) {
        this.priceEdit = priceEdit;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                priceEdit.setText(s);
                priceEdit.setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            priceEdit.setText(s);
            priceEdit.setSelection(2);
        }
        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                priceEdit.setText(s.subSequence(0, 1));
                priceEdit.setSelection(1);
                return;
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
}
