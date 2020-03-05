package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class FiendishServant implements Deathrattle {
    DType type = DType.BUFF;

    @Override
    public void Simulate(List<Card> cards) {

    }

    @Override
    public DType getType() {
        return null;
    }
}
