package com.jason.trade.polo.model;

/**
 * Created by jason on 1/25/17.
 */
public class PoloCurrencyDTO {

    private String minConf;

    private String name;

    private String txFee;

    private String depositAddress;

    private String frozen;

    private String disable;

    private String id;

    private String delisted;

    public String getMinConf() {
        return minConf;
    }

    public void setMinConf(String minConf) {
        this.minConf = minConf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxFee() {
        return txFee;
    }

    public void setTxFee(String txFee) {
        this.txFee = txFee;
    }

    public String getDepositAddress() {
        return depositAddress;
    }

    public void setDepositAddress(String depositAddress) {
        this.depositAddress = depositAddress;
    }

    public String getFrozen() {
        return frozen;
    }

    public void setFrozen(String frozen) {
        this.frozen = frozen;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelisted() {
        return delisted;
    }

    public void setDelisted(String delisted) {
        this.delisted = delisted;
    }
}
