package com.tengfei.fairy.mvp.recyclerview;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tengfei.fairy.R;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.utils.StringUtil;
import com.tengfei.fairy.wedget.EmptyView;

import java.util.List;
import java.util.Objects;

/**
 * @ Description :activity +  RecyclerView  处理基类
 * @ Author 李腾飞
 * @ Time 2021/6/29   14:52
 * @ Version :
 */
public abstract class BaseListActivity<P extends BaseListPresenter,T extends BaseQuickAdapter> extends BaseMvpActivity implements BaseListIView, SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemClickListener {

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected ShimmerRecyclerView mRecyclerView;
    protected T mAdapter;
    protected P mPresenter;
    protected EmptyView mEmptyView;
    protected boolean isFirstLoad =true;
    protected int currentPage= Constants.MyRecyclerViewSetting.PAGE_FIRST;//第一页
    protected DividerItemDecoration divider;//分隔符

    @Override
    protected void initView() {
        setDivider(R.drawable.shape_gray_tiny_divider);
        initRecyclerView();

    }



    @Override
    protected void initData() {
        mPresenter.refresh();
    }

    /**
     * 初始化RecycleView，完成recyclerView设置
     */
    protected  void initRecyclerView(){

    }

    protected abstract T loadAdapter();

    public void refreshRecyclerView(List<T> data) {
        stopRefreshing();
        mRecyclerView.hideShimmerAdapter();
        if (null == mAdapter) {
            return;
        }
        mAdapter.setNewData(data);
        if (StringUtil.isEmpty(data)) {
            mAdapter.setEmptyView(mEmptyView);
            mAdapter.loadMoreEnd(true);
            return;
        }
        if (data.size() < Constants.MyRecyclerViewSetting.PAGE_SIZE) {
            mAdapter.setEnableLoadMore(false);
        }
    }

    protected void stopRefreshing() {
        if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    /**
     * 设置分割线
     * @param resId
     */
    public void setDivider(int resId) {
        if (divider == null) {
            divider = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        }
        if (0 != resId) {
            divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mActivity, resId)));
//            divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mActivity, R.drawable.shape_gray_tiny_divider)));
        }
    }


}
