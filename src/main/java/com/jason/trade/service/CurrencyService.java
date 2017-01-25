package com.jason.trade.service;

import com.google.common.collect.Lists;
import com.jason.trade.cache.CurrencyCache;
import com.jason.trade.command.CurrencyCommand;
import com.jason.trade.mapper.CurrencyMapper;
import com.jason.trade.model.CurrencyDO;
import com.jason.trade.polo.model.PoloCurrencyDTO;
import com.jason.trade.polo.parser.PoloCurrencyParser;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by jason on 1/25/17.
 */
@Service
public class CurrencyService {

    @Resource
    CurrencyMapper currencyMapper;

    @Resource
    CurrencyCommand currencyCommand;

    @Resource
    CurrencyCache currencyCache;

    public void process() {
        String currencyStr = currencyCommand.getHttpResult();
        Map<String,PoloCurrencyDTO> currencyMap = PoloCurrencyParser.parse(currencyStr);

        if(CollectionUtils.isEmpty(currencyMap)) {
            return;
        }

        List<CurrencyDO> currencyDOList = Lists.newArrayList();

        for (Map.Entry<String,PoloCurrencyDTO> entry : currencyMap.entrySet()) {
            if(drop(entry.getValue())) {
                continue;
            }

            CurrencyDO currencyDO = new CurrencyDO();
            currencyDO.setCurrencyCode(entry.getKey());
            currencyDO.setCurrencyName(entry.getValue().getName());

            currencyDOList.add(currencyDO);
        }

        currencyMapper.insertOrUpdateViaList(currencyDOList);
    }

    private boolean drop(PoloCurrencyDTO poloCurrencyDTO) {
        if(poloCurrencyDTO.getDelisted().equals("1")) {
            return true;
        }

        return false;
    }

    public int findCurrencyIdByCode(String code) {
        int rst = -1;
        Map<String,CurrencyDO> currencyDOMap = null;
        try{
            currencyDOMap = currencyCache.getAllCurrency();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(CollectionUtils.isEmpty(currencyDOMap) || !currencyDOMap.containsKey(code)){
            return -1;
        }

        CurrencyDO currencyDO = currencyDOMap.get(code);
        return currencyDO.getId().intValue();
    }
}
