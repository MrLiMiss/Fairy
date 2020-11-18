package com.tengfei.fairy.wedget.wuliView;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-11-18   10:41
 * @ Version :
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.IntentUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @ Description :生活-唔哩资讯
 * @ Author 李腾飞
 * @ Time 2020-10-13   16:42
 * @ Version :
 */
public class WuLiView extends CustomLinearLayout {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    WuLiAdapter mAdapter;
    public WuLiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public int getLayout() {
        return R.layout.view_wulinews;
    }

    @Override
    public void init(AttributeSet attrs) {
        mTvTitle.setText("热门资讯");
        mTvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo:添加更多跳转逻辑
//                IntentUtils.toWebAct(mContext, Constans.getPath("wulinews_more"));
            }
        });
        LinearLayoutManager layoutManage = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManage);
        mAdapter = new WuLiAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setHasFixedSize(true);

    }


    public void setData(List<WuLiVo.ZXListBean> data) {
        mAdapter.setNewData(data);
    }


}