package com.fantasybaby.dee.code.exception.staticerror;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanta
 * @Description
 * @create 2021-03-01 22:03
 */
@RestController("static-error")
@Slf4j
public class StaticErrorController {

    @GetMapping("wrong")
    public void wrong() {
        try {
            createOrderWrong();
        } catch (Exception ex) {
            log.error("createOrder got error", ex);
        }
        try {
            cancelOrderWrong();
        } catch (Exception ex) {
            log.error("cancelOrder got error", ex);
        }
    }

    private void createOrderWrong() {
        //这里有问题
        throw StaticException.ORDEREXISTS;
    }

    private void cancelOrderWrong() {
        //这里有问题
        throw StaticException.ORDEREXISTS;
    }
}
