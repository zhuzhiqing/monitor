package com.jason.trade.command;

import com.google.common.collect.Maps;
import com.jason.trade.enums.PoloCommandEnum;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jason on 2017/1/9.
 */
@Service
public class TickerCommand extends GetCommand {

    protected Map<String, Object> getParam() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("command", PoloCommandEnum.TICKER.getValue());

        return params;
    }

}
