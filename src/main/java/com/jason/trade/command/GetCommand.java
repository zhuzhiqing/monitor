package com.jason.trade.command;

import com.jason.trade.constants.PoloniexConstant;
import com.jason.trade.util.ApacheHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by jason on 2017/1/9.
 */
public abstract class GetCommand implements ICommand {
    private Logger logger = LoggerFactory.getLogger(TickerCommand.class);

    public String getHttpResult(){
        StringBuffer sb = new StringBuffer();
        try {
            sb.append(ApacheHttpClient.httpGetRequest(PoloniexConstant.POLO_HOST, true, getParam()));
        } catch (URISyntaxException e) {
            logger.error("getHttpResult(), result exception, param e={}", e);
        }
        return sb.toString();
    }

    protected abstract  Map<String, Object> getParam() ;
}
