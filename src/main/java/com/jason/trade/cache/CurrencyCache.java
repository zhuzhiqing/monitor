package com.jason.trade.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.jason.trade.mapper.CurrencyMapper;
import com.jason.trade.model.CurrencyDO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jason on 1/25/17.
 */
@Service
public class CurrencyCache {

    @Resource
    CurrencyMapper currencyMapper;


    private LoadingCache<String, Map<String,CurrencyDO>> ALL_CURRENCY = CacheBuilder.newBuilder().
            expireAfterWrite(1, TimeUnit.HOURS).initialCapacity(10).
            maximumSize(200).recordStats().build(new CacheLoader<String, Map<String,CurrencyDO>>() {

        @Override
        public Map<String,CurrencyDO> load(String key) throws Exception {
            return get();
        }

        private Map<String,CurrencyDO> get() {
//            logger.info("get(), info get from interface");

            Map<String,CurrencyDO> currencyDOMap = getAllCurrencyFromDB();
            if (CollectionUtils.isEmpty(currencyDOMap)) {
                return null;
            }

            return currencyDOMap;
        }
    });

    private Map<String,CurrencyDO> getAllCurrencyFromDB() {
        List<CurrencyDO> currencyDOList = currencyMapper.findAll();
        if (CollectionUtils.isEmpty(currencyDOList)) {
            return Collections.EMPTY_MAP;
        }

        Map<String,CurrencyDO> currencyDOMap = Maps.newHashMap();
        for(CurrencyDO currencyDO : currencyDOList) {
            currencyDOMap.put(currencyDO.getCurrencyCode(),currencyDO);
        }

        return currencyDOMap;
    }

    public Map<String,CurrencyDO> getAllCurrency() throws ExecutionException{
        return ALL_CURRENCY.get("CURRENCY");
    }
}
