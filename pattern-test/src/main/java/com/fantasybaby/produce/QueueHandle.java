package com.fantasybaby.produce;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueHandle {
    public static final BlockingQueue messageQueue = new LinkedBlockingDeque();
}
