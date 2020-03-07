package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public abstract class Deathrattle {
    static DType type = null;
    Boolean golden = null;

    public abstract void Simulate(Card card, Player player, List<Card> cards);

    public DType getType() {
        return this.type;
    }
}
