package com.tengfei.fairy.wedget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tengfei.fairy.R;
import com.tengfei.fairy.api.SingleClick;
import com.tengfei.fairy.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ Description :选项View
 * @ Author 李腾飞
 * @ Time 2020/12/23   13:49
 * @ Versio :
 */
class ItemView extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.item_img)
    ImageView item_img;
    @BindView(R.id.item_right)
    ImageView item_right;
    @BindView(R.id.item_text)
    TextView item_text;

    public ItemViewCallBack mCallBack;//回调

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        String test = typeArray.getString(R.styleable.ItemView_text);
        boolean showImg = typeArray.getBoolean(R.styleable.ItemView_showImg, true);
        int testColor = typeArray.getColor(R.styleable.ItemView_textColor, ContextCompat.getColor(getContext(), R.color.colorBlack));
        int testSize = typeArray.getInt(R.styleable.ItemView_textSize, 14);
        //使用完属性资源，手动回收
        typeArray.recycle();
        View itemView = View.inflate(getContext(), R.layout.itemview_layout, this);
        ButterKnife.bind(this);
        if (!showImg) {
            item_img.setVisibility(GONE);
        } else {
            item_img.setVisibility(VISIBLE);
        }
        item_text.setText(test);
        item_text.setTextColor(testColor);
        item_text.setTextSize(testSize);
        item_right.setOnClickListener(this);
    }

    public void setItem_img(int resId){
        if(item_img!=null){
            item_img.setBackgroundResource(resId);
        }

    }

    public void setItem_text(String text){
        if(item_text!=null){
         item_text.setText(text);
        }
    }



    @Override
    @SingleClick(1000)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_right:
                ToastUtil.showMessage(getContext(), "ItemView 右键被点击");
                break;
            default:
                break;

        }
    }

    public void setCallBack(ItemViewCallBack itemViewCallBack){
       mCallBack=itemViewCallBack;
    }

    public interface ItemViewCallBack{
        void back();
        void toImg();

    }
}
