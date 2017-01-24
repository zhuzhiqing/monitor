package com.jason.trade.command;

import com.google.common.collect.Maps;
import com.jason.trade.enums.PoloCommandEnum;

import java.util.Map;

/**
 * Created by jason on 17/1/24.
 */
public class CurrencyCommand extends GetCommand {

    protected Map<String, Object> getParam() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("command", PoloCommandEnum.CURRENCY.getValue());

        return params;
    }
}
