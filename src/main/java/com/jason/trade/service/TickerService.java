package com.jason.trade.service;

import com.google.common.collect.Lists;
import com.jason.trade.command.TickerCommand;
import com.jason.trade.mapper.TickMapper;
import com.jason.trade.model.TickDO;
import com.jason.trade.polo.model.PoloTickDTO;
import com.jason.trade.polo.parser.PoloTickParser;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.jason.trade.constants.PoloniexConstant.STOSHI;

/**
 * Created by jason on 17/1/24.
 */
@Service
public class TickerService {

    @Resource
    TickMapper tickDao;

    @Resource
    TickerCommand tickerCommand;

    @Resource
    CurrencyService currencyService;

    public void process() {
        String tickStr = tickerCommand.getHttpResult();
        Map<String, PoloTickDTO> tickDTOMap = PoloTickParser.parse(tickStr);

        if(CollectionUtils.isEmpty(tickDTOMap)) {
            return;
        }

        List<TickDO> tickDOList = Lists.newArrayList();

        for (Map.Entry<String,PoloTickDTO> entry : tickDTOMap.entrySet()) {
            String name = entry.getKey();
            if(!name.startsWith("BTC_")){
                continue;
            }
            String code = name.substring("BTC_".length());
            int currencyId = currencyService.findCurrencyIdByCode(code);
            if(currencyId < 0) {
                continue;
            }

            TickDO tickDO = convertPoloTick(entry.getValue());
            tickDO.setCurrencyId(currencyId);

            tickDOList.add(tickDO);
        }

        tickDao.insertViaList(tickDOList);
    }

    private TickDO convertPoloTick(PoloTickDTO polo){
        TickDO tick = null;

        try{
            tick = new TickDO();
            tick.setPercentChange((int)(Double.parseDouble(polo.getPercentChange())*10000));    //百分比乘以一万
            tick.setHigh24h(string2Integer(polo.getHigh24hr()));
            tick.setLow24h(string2Integer(polo.getLow24hr()));
            tick.setLowestAsk(string2Integer(polo.getLowestAsk()));
            tick.setPrice(string2Integer(polo.getLast()));
            tick.setHigestBid(string2Integer(polo.getHighestBid()));
            tick.setQuoteVol((int)(Double.parseDouble(polo.getQuoteVolume())));
            tick.setBaseVol((int)(Double.parseDouble(polo.getBaseVolume())));
        }catch (Exception e) {
            e.printStackTrace();
        }

        return tick;
    }

    private Integer string2Integer(String str){
        return STOSHI.multiply(new BigDecimal(str)).intValue();
    }
}
