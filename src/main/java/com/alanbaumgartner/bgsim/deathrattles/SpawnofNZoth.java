package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class SpawnofNZoth extends Deathrattle {


    @Override
    public void init() {

        type = DType.BUFF;
    }

    @Override
    public List<Card> Simulate(Card card) {
        return null;
    }

    @Override
    public void Simulate(Card card, Player player) {
        if (card.isGold()) {
            for (Card c : player.getMinions()) {
                c.setAttack(c.getAttack() + 2);
                c.setHealth(c.getHealth() + 2);
            }
        } else {
            for (Card c : player.getMinions()) {
                c.setAttack(c.getAttack() + 1);
                c.setHealth(c.getHealth() + 1);
            }
        }
    }
}
