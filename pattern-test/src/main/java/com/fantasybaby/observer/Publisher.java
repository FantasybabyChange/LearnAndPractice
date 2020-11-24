package com.fantasybaby.observer;

import java.util.Observable;

public class Publisher extends Observable {
    public void sendMessage() {
        this.setChanged();
        this.notifyObservers("hello");
    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.addObserver(new PowerObserver());
        publisher.sendMessage();
    }
}
