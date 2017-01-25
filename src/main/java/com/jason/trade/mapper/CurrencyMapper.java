package com.jason.trade.mapper;

import com.jason.trade.model.CurrencyDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 1/25/17.
 */
@Repository
public interface CurrencyMapper {

    int insert(CurrencyDO record);

    int insertOrUpdateViaList(List<CurrencyDO> currencyDOList);

    List<CurrencyDO> findAll();
}
