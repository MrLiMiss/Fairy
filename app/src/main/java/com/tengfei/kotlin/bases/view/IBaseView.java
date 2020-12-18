package com.tengfei.kotlin.bases.view;

/**
 * Created by mfhj-18 on 17/5/26.
 */

public interface IBaseView {
     void showLoadingDiaglog(String msg);

     void dissmissLoadingDialog();

     void showToast(String msg);
}
