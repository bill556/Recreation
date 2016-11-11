package com.bill.recreation.event;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class ChannelItemMoveEvent {
    private int fromPosition;
    private int toPosition;

    public int getFromPosition() {
        return fromPosition;
    }

    public int getToPosition() {
        return toPosition;
    }

    public ChannelItemMoveEvent(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;

    }
}
