package com.fantasybaby.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;
@Slf4j
public class PowerObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        log.info("update-----{}",arg);
    }
}
