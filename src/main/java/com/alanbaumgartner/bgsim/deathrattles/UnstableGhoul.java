package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class UnstableGhoul extends Deathrattle {

    @Override
    public void init() {

        type = DType.ATTACK;
    }

    @Override
    public List<Card> Simulate(Card card) {
        return null;
    }

    @Override
    public void Simulate(Card card, Player player) {
        for (Card c : card.getPlayer().getMinions()) {
            c.setHealth(c.getHealth() - 1);
        }
        for (Card c : player.getMinions()) {
            c.setHealth(c.getHealth() - 1);
        }
    }
}
