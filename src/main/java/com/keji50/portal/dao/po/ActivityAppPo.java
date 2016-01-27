package com.keji50.portal.dao.po;

import java.util.Date;

public class ActivityAppPo {
    private String activityId;

    private Integer userId;

    private String mobile;

    private Date appDt;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getAppDt() {
        return appDt;
    }

    public void setAppDt(Date appDt) {
        this.appDt = appDt;
    }
}