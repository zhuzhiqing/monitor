package com.jason.trade.mapper;

import com.jason.trade.model.Tick;
import org.springframework.stereotype.Repository;

@Repository
public interface TickMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticker
     *
     * @mbg.generated
     */
    int insert(Tick record);

}