package com.tengfei.fairy.wedget.wuliView;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * @ Description :生活页-唔哩资讯
 * @ Author 李腾飞
 * @ Time 2020-10-13   15:33
 * @ Version :
 *
 */

public class WuLiVo extends BaseVo {


    /**
     * ActionStartTime : 1602652839988
     * HOST_IP : 192.168.103.234
     * MSG : 交易成功
     * RED_BAG : {}
     * REQ_URL : //common/queryZiXunList.do
     * STATUS : 1
     * USE_TIME : 266
     * ZXList : [{"category":"理财","createTime":"2020-09-21 01:11:17","editTime":"2020-09-21 01:11:17","images":"http://img.9liuda.com/image/202009/17/fa56e33325ba052ef5437beeab5d0b2b?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"02","isHotSpot":"N","messageId":"2ea732c40b9caca0","messageType":"NEWS","origin":"新鲜事儿1","publishTime":"2020-09-21 01:11:19","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=aijbq7t9&mid=c4d3818ac3d38d77:1212418&ct=NEWS","tags":"原油需求1","title":"2020年9月17日市场分析1"},{"category":"理财","createTime":"2020-09-21 01:11:17","editTime":"2020-09-21 01:11:17","images":"http://img.9liuda.com/image/202009/17/fa56e33325ba052ef5437beeab5d0b2b?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"02","isHotSpot":"Y","messageId":"2ea732c40b9caca0","messageType":"NEWS","origin":"新鲜事儿","publishTime":"2020-09-21 01:11:18","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=aijbq7t9&mid=c4d3818ac3d38d77:1212418&ct=NEWS","tags":"原油需求","title":"2020年9月17日市场分析"},{"category":"股市","createTime":"2020-09-17 11:33:16","editTime":"2020-09-19 13:50:00","images":"http://img.9liuda.com/image/202009/17/e9e33b3172142c82b6e1e57e0328fe5e?imageMogr2/auto-orient/interlace/1/format/jpg/size-limit/$(fsize)","informationType":"02","isHotSpot":"N","messageId":"3ba2dbe3f9609c4b","messageType":"NEWS","origin":"新资讯","publishTime":"2020-09-17 12:34:16","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=28s6e0e1&mid=3ba2dbe3f9609c4b:1213040&ct=NEWS","tags":"市场板块","title":"收评:股指尾盘冲高回落沪指跌0.41% 科技股强势"},{"category":"财经消息","createTime":"2020-09-19 11:33:16","editTime":"2020-09-19 13:50:00","images":"http://img.9liuda.com/image/202009/17/66a0f6baf1c69de46ae4a79ab6714f80aef0?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"02","isHotSpot":"N","messageId":"709a2be1b82af43a","messageType":"NEWS","origin":"新闻早知道","publishTime":"2020-09-17 12:34:16","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=28s6e0e1&mid=709a2be1b82af43a:1201007&ct=NEWS","tags":"鲍威尔","title":"1政策声明是为了糊弄市场？鲍威尔的语言艺术令投资者迷失"},{"category":"财经消息","createTime":"2020-09-19 11:33:16","editTime":"2020-09-19 13:50:00","images":"http://img.9liuda.com/image/202009/17/ae32199a466163d390ee31ab78e4b813?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"01","isHotSpot":"N","messageId":"709a2be1b82af43a","messageType":"NEWS","origin":"新闻早知道","publishTime":"2020-09-17 12:34:16","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=28s6e0e1&mid=709a2be1b82af43a:1201007&ct=NEWS","tags":"鲍威尔","title":"2政策声明是为了糊弄市场？鲍威尔的语言艺术令投资者迷失"},{"category":"财经消息","createTime":"2020-09-19 11:33:16","editTime":"2020-09-19 13:50:00","images":"http://img.9liuda.com/image/202009/17/efb79889a1eefe0e6e38ac1961d2931f?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"01","isHotSpot":"N","messageId":"709a2be1b82af43a","messageType":"NEWS","origin":"新闻早知道","publishTime":"2020-09-17 12:34:16","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=28s6e0e1&mid=709a2be1b82af43a:1201007&ct=NEWS","tags":"鲍威尔","title":"3政策声明是为了糊弄市场？鲍威尔的语言艺术令投资者迷失"},{"category":"财经消息","createTime":"2020-09-19 11:33:16","editTime":"2020-09-19 13:50:00","images":"http://img.9liuda.com/image/202009/17/0c29abce48878e1ae69f1358d65fc83c?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"01","isHotSpot":"N","messageId":"709a2be1b82af43a","messageType":"NEWS","origin":"新闻早知道","publishTime":"2020-09-17 12:34:16","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=28s6e0e1&mid=709a2be1b82af43a:1201007&ct=NEWS","tags":"鲍威尔","title":"4政策声明是为了糊弄市场？鲍威尔的语言艺术令投资者迷失"},{"category":"财经消息","createTime":"2020-09-19 11:33:16","editTime":"2020-09-19 13:50:00","images":"http://img.9liuda.com/image/202009/17/4dd9bc56d2794efb30aa725739b198b1?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"03","isHotSpot":"N","messageId":"709a2be1b82af43a","messageType":"NEWS","origin":"新闻早知道","publishTime":"2020-09-17 12:34:16","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=28s6e0e1&mid=709a2be1b82af43a:1201007&ct=NEWS","tags":"鲍威尔","title":"5政策声明是为了糊弄市场？鲍威尔的语言艺术令投资者迷失"},{"category":"财经消息","createTime":"2020-09-17 11:33:16","editTime":"2020-09-19 15:50:00","images":"http://img.9liuda.com/image/202009/17/38360fa9d71d66855251e07a40860a81?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"02","isHotSpot":"N","messageId":"8cd9a1ee0832ba9d","messageType":"NEWS","origin":"午间播报","publishTime":"2020-09-17 11:34:19","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=28s6e0e1&mid=8cd9a1ee0832ba9d:1213750&ct=NEWS","tags":"国家经济","title":"GDP前十五强：亚洲上榜5国、欧洲有6国、美洲上榜3国、非洲又是零"},{"category":"理财","createTime":"2020-09-17 11:33:16","editTime":"2020-09-19 14:50:00","images":"http://img.9liuda.com/image/202009/17/d696c3c9fe96430268e4fcc5c86702d0?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)","informationType":"02","isHotSpot":"Y","messageId":"d2ddda56033f7dbc","messageType":"NEWS","origin":"资讯站","publishTime":"2020-09-17 11:34:18","shareLink":"http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=aijbq7t9&mid=d2ddda56033f7dbc:200019&ct=NEWS","tags":"贵州","title":"收购贵州高速股份茅台集团拟发行150亿债券"}]
     * ZXSize : 15
     * cn.com.yitong.ares.core.ThreadContext_DYNAMIC_KEY : DR77CkmAhGkuZ2Ey
     * pageNum : 1
     */

    private long ActionStartTime;
    private String HOST_IP;
    private String MSG;
    private REDBAGBean RED_BAG;
    private String REQ_URL;
    @SerializedName("STATUS")
    public String STATUS;
    private String USE_TIME;
    private int ZXSize;
    @SerializedName("cn.com.yitong.ares.core.ThreadContext_DYNAMIC_KEY")
    private String _$CnComYitongAresCoreThreadContext_DYNAMIC_KEY187; // FIXME check this code
    private String pageNum;
    @SerializedName("ZXList")
    public ArrayList<ZXListBean> ZXList;

    public long getActionStartTime() {
        return ActionStartTime;
    }

    public void setActionStartTime(long ActionStartTime) {
        this.ActionStartTime = ActionStartTime;
    }

    public String getHOST_IP() {
        return HOST_IP;
    }

    public void setHOST_IP(String HOST_IP) {
        this.HOST_IP = HOST_IP;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public REDBAGBean getRED_BAG() {
        return RED_BAG;
    }

    public void setRED_BAG(REDBAGBean RED_BAG) {
        this.RED_BAG = RED_BAG;
    }

    public String getREQ_URL() {
        return REQ_URL;
    }

    public void setREQ_URL(String REQ_URL) {
        this.REQ_URL = REQ_URL;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getUSE_TIME() {
        return USE_TIME;
    }

    public void setUSE_TIME(String USE_TIME) {
        this.USE_TIME = USE_TIME;
    }

    public int getZXSize() {
        return ZXSize;
    }

    public void setZXSize(int ZXSize) {
        this.ZXSize = ZXSize;
    }

    public String get_$CnComYitongAresCoreThreadContext_DYNAMIC_KEY187() {
        return _$CnComYitongAresCoreThreadContext_DYNAMIC_KEY187;
    }

    public void set_$CnComYitongAresCoreThreadContext_DYNAMIC_KEY187(String _$CnComYitongAresCoreThreadContext_DYNAMIC_KEY187) {
        this._$CnComYitongAresCoreThreadContext_DYNAMIC_KEY187 = _$CnComYitongAresCoreThreadContext_DYNAMIC_KEY187;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public ArrayList<ZXListBean> getZXList() {
        return ZXList;
    }

    public void setZXList(ArrayList<ZXListBean> ZXList) {
        this.ZXList = ZXList;
    }

    public static class REDBAGBean {
    }

    public static class ZXListBean {
        /**
         * category : 理财
         * createTime : 2020-09-21 01:11:17
         * editTime : 2020-09-21 01:11:17
         * images : http://img.9liuda.com/image/202009/17/fa56e33325ba052ef5437beeab5d0b2b?imageMogr2/auto-orient/interlace/1/format/jpg/thumbnail/500x/size-limit/$(fsize)
         * informationType : 02
         * isHotSpot : N
         * messageId : 2ea732c40b9caca0
         * messageType : NEWS
         * origin : 新鲜事儿1
         * publishTime : 2020-09-21 01:11:19
         * shareLink : http://api.wuliapi.com/mf/detail?aid=3aq382ji&cid=aijbq7t9&mid=c4d3818ac3d38d77:1212418&ct=NEWS
         * tags : 原油需求1
         * title : 2020年9月17日市场分析1
         */

        private String category;
        private String createTime;
        private String editTime;
        private String images;
        private String informationType;
        private String isHotSpot;
        private String messageId;
        private String messageType;
        private String origin;
        private String publishTime;
        private String shareLink;
        private String tags;
        private String title;

        private String showline;
        public String getShowline() {
            return showline;
        }

        public void setShowline(String showline) {
            this.showline = showline;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEditTime() {
            return editTime;
        }

        public void setEditTime(String editTime) {
            this.editTime = editTime;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getInformationType() {
            return informationType;
        }

        public void setInformationType(String informationType) {
            this.informationType = informationType;
        }

        public String getIsHotSpot() {
            return isHotSpot;
        }

        public void setIsHotSpot(String isHotSpot) {
            this.isHotSpot = isHotSpot;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getShareLink() {
            return shareLink;
        }

        public void setShareLink(String shareLink) {
            this.shareLink = shareLink;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
