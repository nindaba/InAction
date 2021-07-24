package com.yadlings.streamprocessor.Stock;

import com.yadlings.avro.SymbolShares;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public class Topper{
    public List<SymbolShares> add(List<SymbolShares> topList, int  top, SymbolShares value){
        Comparator<SymbolShares> comparator = (t1,t2)->t2.getTotal()- t1.getTotal();
        topList.add(value);
        topList.sort(comparator);
        if(topList.size()>top){
            topList.remove(top);
        }
        return topList;
    }
    public List<SymbolShares> remove(List<SymbolShares> topList, SymbolShares value) {
        int index = topList.indexOf(value);
        if(index>-1) topList.remove(index);
        return topList;
    }
}
