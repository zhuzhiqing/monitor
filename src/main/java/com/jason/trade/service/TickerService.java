package com.jason.trade.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        JSONObject jsonObject = (JSONObject)JSON.parse(tickStr);
//        jsonObject
        jsonObject.entrySet();
        System.out.println(jsonObject);
    }
}
