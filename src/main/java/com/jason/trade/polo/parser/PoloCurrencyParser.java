package com.jason.trade.polo.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.jason.trade.polo.model.PoloCurrencyDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jason on 1/25/17.
 */
public class PoloCurrencyParser {

    private PoloCurrencyParser() {
        //禁止实例化
    }

    public static Map<String, PoloCurrencyDTO> parse(String currencyStr) {
        if(Strings.isNullOrEmpty(currencyStr)) {
            return Collections.EMPTY_MAP;
        }

        Map<String, PoloCurrencyDTO> rst = new HashMap<String, PoloCurrencyDTO>();
        try {
            JSONObject jsonObject = (JSONObject) JSON.parse(currencyStr);
            Set<Map.Entry<String, Object>> set = jsonObject.entrySet();
            for (Map.Entry<String, Object> item : set) {
                String code = item.getKey();
                PoloCurrencyDTO poloCurrencyDTO = JSON.parseObject(item.getValue().toString(), PoloCurrencyDTO.class);
                if (Strings.isNullOrEmpty(code) || poloCurrencyDTO == null) {
                    continue;
                }

                rst.put(code, poloCurrencyDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rst;
    }
}
