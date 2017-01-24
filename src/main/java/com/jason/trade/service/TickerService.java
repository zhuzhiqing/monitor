package com.jason.trade.service;

import com.jason.trade.mapper.TickMapper;
import com.jason.trade.model.Tick;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jason on 17/1/24.
 */
@Service
public class TickerService {

    @Resource
    TickMapper tickDao;

    public void insert(){
        Tick record = new Tick();
        record.setBaseVol(10);
        record.setCurrencyId(1);
        record.setPrice(1);
        record.setHigestBid(1);
        record.setHigh24h(1);
        record.setLow24h(1);
        record.setLowestAsk(1);
        record.setPercentChange(1);
        record.setQuoteVol(1);

        tickDao.insert(record);
    }
}
