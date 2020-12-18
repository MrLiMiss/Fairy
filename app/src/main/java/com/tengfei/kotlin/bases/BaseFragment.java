package com.tengfei.kotlin.bases;

import android.content.Context;
import android.view.View;

import com.beebank.newbee.R;
import com.beebank.newbee.SDMoneyApplication;
import com.beebank.newbee.common.ServiceManager;
import com.beebank.newbee.common.utils.LogUtil;
import com.beebank.newbee.widgets.CommonDialog;
import com.beebank.newbee.widgets.CommonLoadingDialog;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mfhj-18 on 16/7/27.
 */
public abstract class BaseFragment extends Fragment  {
    private Unbinder unbinder;

    protected Context mContext;
    protected ServiceManager mServiceManager;

    protected CommonLoadingDialog loadingDialog;

    public BaseFragment() {
        mServiceManager = SDMoneyApplication.getInstance().getServiceManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected void bindViews(Object object, View view) {
        unbinder = ButterKnife.bind(object, view);
    }

    protected void unbindViews() {
        if (unbinder != null) {
            try {
                unbinder.unbind();
            } catch (Exception e) {
                LogUtil.e(e.getMessage().toString());
            }
        }
    }

    protected void showLoading() {
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } else {
            loadingDialog = new CommonLoadingDialog.Builder(getContext()).build();
        }
        loadingDialog.show();
    }

    protected void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    protected void analyticsEvent(String id) {
    }

    public boolean onBackPressed() {
        return false;
    }


    protected void showTipsDialog(String msg) {
        CommonDialog.Builder commonDialog = new CommonDialog.Builder(getActivity());
        commonDialog.addBankLimit();
        commonDialog.setMessage(msg);
        commonDialog.setSingleButton(getString(R.string.sdf_common_ok), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).build().show();
    }
}
