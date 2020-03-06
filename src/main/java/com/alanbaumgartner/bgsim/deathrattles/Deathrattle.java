package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public abstract class Deathrattle {
    DType type = null;
    Boolean golden = null;

    abstract void Simulate(List<Card> cards);

    DType getType() {
        return this.type;
    }
}
