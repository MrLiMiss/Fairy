package com.tengfei.fairy.widget.wuliView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.StringUtil;
import com.tengfei.fairy.utils.ToastUtils;
import com.tengfei.fairy.utils.image.ImageManager;

/**
 * @ Description :唔哩头条recycleview
 * @ Author 李腾飞
 * @ Time 2020-11-18   11:04
 * @ Version :
 */
public class WuLiAdapter extends BaseQuickAdapter<WuLiVo.ZXListBean, BaseViewHolder> {
    private Context mContext;
    public WuLiAdapter(Context context) {
        super(R.layout.item_wuli_news, null);
        this.mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WuLiVo.ZXListBean item) {
        LinearLayout linearLayout=helper.getView(R.id.ll_news_container);
        TextView title = helper.getView(R.id.tv_life_news_title);//标题
        TextView tv_news_media=helper.getView(R.id.tv_news_media);//出版社
        TextView tv_life_news_time=helper.getView(R.id.tv_life_news_time);//发布时间
        ImageView mImgIcon = helper.getView(R.id.im_wuli_news);//资讯图片
        View line=helper.getView(R.id.view_line);
        if (null == item) {
            return;
        }
        if(StringUtil.isNotEmpty(item.getShowline())){
            line.setVisibility(View.GONE);
//            linearLayout.setPadding(30,30,30,30);
        }
        if (StringUtil.isNotEmpty(item.getImages())) {
//            ImageManager.loadImgFromUrl(mContext, item.getImages(), mImgIcon, R.drawable.icon_defaul);
            ImageManager.showOldImageRoundBanner(mContext, item.getImages(), mImgIcon, 4);


        }else {
            mImgIcon.setImageResource(R.drawable.icon_defaul);
        }
        title.setText(item.getTitle());
        tv_news_media.setText(item.getOrigin());//出版社
        tv_life_news_time.setText(item.getPublishTime());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentUtils.toWebAct(mContext,item.getShareLink());
                ToastUtils.showToast("wuliView item点击！");

            }
        });

    }



}