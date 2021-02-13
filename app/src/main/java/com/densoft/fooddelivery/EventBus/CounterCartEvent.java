package com.densoft.fooddelivery.EventBus;

public class CounterCartEvent {

    private boolean success;

    public CounterCartEvent(boolean b) {
        this.success = b;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
