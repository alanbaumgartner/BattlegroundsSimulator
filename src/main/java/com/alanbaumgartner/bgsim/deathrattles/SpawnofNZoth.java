package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class SpawnofNZoth extends Deathrattle {


    static {
        type = DType.BUFF;
    }

    @Override
    public List<Card> Simulate(Card card, Player player, List<Card> cards) {
        player.removeCard(card);
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
        return null;
    }


}
