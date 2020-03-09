package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class UnstableGhoul extends Deathrattle {

    static {
        type = DType.ATTACK;
    }

    @Override
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        List<Card> killed = new ArrayList<>();
        owner.removeCard(card);
        for (Card c : owner.getMinions()) {
            c.setHealth(c.getHealth() - 1);
            if (c.isDead()) {
                killed.add(c);
            }
        }
        for (Card c : opponent.getMinions()) {
            c.setHealth(c.getHealth() - 1);
            if (c.isDead()) {
                killed.add(c);
            }
        }
        return killed;
    }
}
