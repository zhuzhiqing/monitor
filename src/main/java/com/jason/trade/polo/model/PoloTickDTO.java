package com.jason.trade.polo.model;

/**
 * Created by jason on 1/25/17.
 */
public class PoloTickDTO {

    private String percentChange;

    private String high24hr;

    private String last;

    private String highestBid;

    private String id;

    private String quoteVolume;

    private String baseVolume;

    private String isFrozen;

    private String lowestAsk;

    private String low24hr;

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getHigh24hr() {
        return high24hr;
    }

    public void setHigh24hr(String high24hr) {
        this.high24hr = high24hr;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public String getLow24hr() {
        return low24hr;
    }

    public void setLow24hr(String low24hr) {
        this.low24hr = low24hr;
    }
}
