package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public abstract class Deathrattle {
    protected static DType type = null;

    public abstract List<Card> Simulate(Card card, Player player, List<Card> cards);

    public DType getType() {
        return type;
    }

    protected int getIndex(Card card, Player player) {
        return Math.max(player.getMinions().indexOf(card), 0);
    }
}
