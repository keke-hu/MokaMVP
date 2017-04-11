package com.cdwx.moka.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KeKe on 2016/6/30.
 */
public class MainInfoBean implements Serializable {
    private static final long serialVersionUID = 5700379542385619938L;
    private String appLink;
    private String upUrl;
    private String upInfo;
    private List<MainImgBean> imgs;
    private String contact;
    private String showShare;

    public String getAppLink() {
        return appLink;
    }

    public String getUpUrl() {
        return upUrl;
    }

    public String getUpInfo() {
        return upInfo;
    }

    public List<MainImgBean> getImgs() {
        return imgs!=null?imgs:new ArrayList<MainImgBean>();
    }

    public String getContact() {
        return contact;
    }

    public String getShowShare() {
        return showShare;
    }
}
