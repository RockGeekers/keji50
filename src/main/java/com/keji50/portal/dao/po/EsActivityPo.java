package com.keji50.portal.dao.po;

import java.math.BigDecimal;
import java.util.Date;

public class EsActivityPo {
    private String id;

    private String title;

    private BigDecimal price;

    private Date begintime;

    private Date endtime;

    private Integer userId;

    private String address;

    private String imageId;

    private Date appBeginDt;

    private Date appEndDt;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId == null ? null : imageId.trim();
    }

    public Date getAppBeginDt() {
        return appBeginDt;
    }

    public void setAppBeginDt(Date appBeginDt) {
        this.appBeginDt = appBeginDt;
    }

    public Date getAppEndDt() {
        return appEndDt;
    }

    public void setAppEndDt(Date appEndDt) {
        this.appEndDt = appEndDt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}