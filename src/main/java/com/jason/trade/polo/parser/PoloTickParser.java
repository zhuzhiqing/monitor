package com.jason.trade.polo.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.jason.trade.polo.model.PoloTickDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jason on 1/25/17.
 */
public class PoloTickParser {

    private PoloTickParser() {
        //禁止实例化
    }

    public static Map<String, PoloTickDTO> parse(String tickStr) {
        if(Strings.isNullOrEmpty(tickStr)) {
            return Collections.EMPTY_MAP;
        }

        Map<String, PoloTickDTO> rst = new HashMap<String, PoloTickDTO>();
        try {
            JSONObject jsonObject = (JSONObject) JSON.parse(tickStr);
            Set<Map.Entry<String, Object>> set = jsonObject.entrySet();
            for (Map.Entry<String, Object> item : set) {
                String code = item.getKey();
                PoloTickDTO poloTickDTO = JSON.parseObject(item.getValue().toString(), PoloTickDTO.class);
                if (Strings.isNullOrEmpty(code) || poloTickDTO == null) {
                    continue;
                }

                rst.put(code, poloTickDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rst;
    }
}
