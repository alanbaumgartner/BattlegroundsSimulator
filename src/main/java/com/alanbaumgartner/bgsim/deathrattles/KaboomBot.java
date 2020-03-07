package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class KaboomBot extends Deathrattle {


    static {
        type = DType.ATTACK;
    }

    @Override
    public List<Card> Simulate(Card card, Player player, List<Card> cards) {
        List<Card> killed = new ArrayList<>();
        player.removeCard(card);
        if (cards.size() <= 0) {
            return null;
        }
        int index = Main.getRandomInteger(cards.size());
        Card c = cards.get(index);
        c.setHealth(c.getHealth() - 4);
        if (c.isDead()) {
            killed.add(c);
        }
        if (card.isGold()) {
            index = Main.getUniqueRandomInteger(cards.size(), new ArrayList<>(index));
            c = cards.get(index);
            c.setHealth(c.getHealth() - 4);
            if (c.isDead()) {
                killed.add(c);
            }
        }
        return killed;
    }


}
