package com.SLotus.dsms.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务异常
 */
@Slf4j
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
        log.error("业务异常:{}", message);
    }


}
