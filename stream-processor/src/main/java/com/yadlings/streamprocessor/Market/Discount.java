package com.yadlings.streamprocessor.Market;

import com.yadlings.avro.Purchase;
import org.apache.kafka.streams.kstream.ValueJoiner;

public class Discount implements ValueJoiner<Purchase,Purchase,Purchase> {
    @Override
    public Purchase apply(Purchase purchase, Purchase purchase1) {
        purchase1.setTotal(purchase1.getTotal()-purchase.getTotal()*0.10);
        return purchase1;
    }
}
