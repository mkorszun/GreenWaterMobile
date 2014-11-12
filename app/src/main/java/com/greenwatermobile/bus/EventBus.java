package com.greenwatermobile.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class EventBus extends Bus {

    private static final Object lock = new Object();

    private static Bus bus;

    private EventBus() {
    }

    public static Bus getInstance() {
        synchronized (lock) {
            if (bus == null) {
                bus = new Bus(ThreadEnforcer.ANY);
            }
            return bus;
        }
    }
}