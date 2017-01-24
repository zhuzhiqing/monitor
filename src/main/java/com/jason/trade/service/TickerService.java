package com.jason.trade.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jason.trade.command.TickerCommand;
import com.jason.trade.mapper.TickMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jason on 17/1/24.
 */
@Service
public class TickerService {

    @Resource
    TickMapper tickDao;

    @Resource
    TickerCommand tickerCommand;

    public void process() {
        String tickStr = tickerCommand.getHttpResult();
        JSONArray jsonArray = JSON.parseArray(tickStr);

        System.out.println(jsonArray);
    }
}
