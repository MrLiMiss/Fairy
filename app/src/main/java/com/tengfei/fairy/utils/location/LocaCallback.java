package com.tengfei.fairy.utils.location;

import android.location.GnssStatus;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2021/7/2   17:16
 * @ Version :
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class LocaCallback extends GnssStatus.Callback {
    @Override
    public void onSatelliteStatusChanged(GnssStatus status) {
        super.onSatelliteStatusChanged(status);
        int satelliteCount=status.getSatelliteCount();
        //解析组装卫星信息
        makeGnssStatus(status,satelliteCount);
    }

    @Override
    public void onStarted() {
        super.onStarted();
    }

    @Override
    public void onStopped() {
        super.onStopped();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void makeGnssStatus(GnssStatus status,int satelliteCount){
        //当前可以获取到的卫星总数，然后遍历
        if(satelliteCount>0){
            for (int i = 0; i < satelliteCount; i++) {
                //GnssStatus的大部分方法参数传入的就是卫星数量的角标
                //获取卫星类型
                int type=status.getConstellationType(i);
                if(GnssStatus.CONSTELLATION_BEIDOU==type){
                    //北斗卫星类型的判断

                }
            }
        }
    }


}
