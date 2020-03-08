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

    public void addTokens(boolean golden, int index, Card[] cards, Player player) {
        if (golden) {
            for (Card c : cards) {
                c.setHealth(c.getHealth() * 2);
                c.setAttack(c.getAttack() * 2);
                player.addCard(index, c);
            }
        } else {
            for (Card c : cards) {
                player.addCard(index, c);
            }
        }
    }

    protected int getIndex(Card card, Player player) {
        return Math.max(player.getMinions().indexOf(card), 0);
    }
}
