package com.jason.trade.mapper;

import com.jason.trade.model.TickDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TickMapper {

    int insert(TickDO record);

    int insertViaList(List<TickDO> tickDOList);
}