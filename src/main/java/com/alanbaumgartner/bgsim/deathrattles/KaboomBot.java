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
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        List<Card> killed = new ArrayList<>();
        owner.removeCard(card);
        if (opponent.getMinions().size() <= 0) {
            return null;
        }
        int attackIndex = Main.getRandomInteger(opponent.getMinions().size());
        Card c = opponent.getMinions().get(attackIndex);
        c.setHealth(c.getHealth() - 4);
        if (c.isDead()) {
            killed.add(c);
        }
        if (card.isGold()) {
            index = Main.getUniqueRandomInteger(opponent.getMinions().size(), new ArrayList<>(index));
            c = opponent.getMinions().get(index);
            c.setHealth(c.getHealth() - 4);
            if (c.isDead()) {
                killed.add(c);
            }
        }
        return killed;
    }


}
