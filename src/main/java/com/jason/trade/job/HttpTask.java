package com.jason.trade.job;

import com.google.common.base.Stopwatch;
import com.jason.trade.command.TickerCommand;
import com.jason.trade.util.ApacheHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by jason on 2016/12/6.
 */

@Component
public class HttpTask {
    private Logger logger = LoggerFactory.getLogger(TickerCommand.class);

    @Resource
    TickerCommand tickerCommand;

    @Scheduled(fixedRate = 10 * 1000)
    public void task() throws InterruptedException {
        String rst = tickerCommand.getHttpResult();
        logger.info(rst);
    }

    @Async
    private void getOrderBook() {
        System.out.println("##########getOrderBook#########");
        Stopwatch stopwatch = Stopwatch.createStarted();
        String url = "https://poloniex.com/public?command=returnOrderBook&currencyPair=all&depth=10";
        System.out.println(ApacheHttpClient.httpGetRequest(url, true));
    }

    private void getCurrencies() {
        System.out.println("##########getCurrencies#########");
        Stopwatch stopwatch = Stopwatch.createStarted();
        String url = "https://poloniex.com/public?command=returnCurrencies";
        System.out.println(ApacheHttpClient.httpGetRequest(url, true));

        stopwatch.stop();
    }
}
