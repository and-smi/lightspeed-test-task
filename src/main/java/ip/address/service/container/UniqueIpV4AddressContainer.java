package main.java.ip.address.service.container;

import java.util.BitSet;

public class UniqueIpV4AddressContainer implements UniqueIntIpAddressContainer {

    private final BitSet positiveValuesContainer = new BitSet(Integer.MAX_VALUE);
    private final BitSet negativeValuesContainer = new BitSet(Integer.MAX_VALUE);

    @Override
    public void add(int value) {
        if (value >= 0) {
            positiveValuesContainer.set(value);
        } else {
            negativeValuesContainer.set(~value);
        }
    }

    @Override
    public long size() {
        return (long) positiveValuesContainer.cardinality() + negativeValuesContainer.cardinality();
    }
}
