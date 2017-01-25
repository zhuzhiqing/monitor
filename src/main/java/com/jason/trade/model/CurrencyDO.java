package com.jason.trade.model;

import java.util.Date;

/**
 * Created by jason on 17/1/24.
 */
public class CurrencyDO {
    private Long id;

    private Long currencyId = 1L;

    private String currencyName;

    private String currencyCode;

    private Date utmUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getUtmUpdate() {
        return utmUpdate;
    }

    public void setUtmUpdate(Date utmUpdate) {
        this.utmUpdate = utmUpdate;
    }

    @Override
    public String toString() {
        return "CurrencyDO{" +
                "id=" + id +
                ", currencyId=" + currencyId +
                ", currencyName='" + currencyName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", utmUpdate=" + utmUpdate +
                '}';
    }
}
