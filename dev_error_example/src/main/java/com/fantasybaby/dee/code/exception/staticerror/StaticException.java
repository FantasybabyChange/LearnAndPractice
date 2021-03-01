package com.fantasybaby.dee.code.exception.staticerror;

import com.fantasybaby.dee.code.exception.common.BusinessException;

/**
 * static exception
 * @author fantasybaby
 */
public class StaticException {
    public static BusinessException ORDEREXISTS = new BusinessException("订单已经存在", 3001);
}
