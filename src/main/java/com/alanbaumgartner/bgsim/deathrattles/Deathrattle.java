package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public interface Deathrattle {
    DType type = null;
    Boolean golden = null;

    void Simulate(List<Card> cards);

    DType getType();
}
