package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class KaboomBot extends Deathrattle {


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
        if (player.getMinions().size() > 0) {
            int attackIndex = Main.getRandomInteger(player.getMinions().size());
            Card c = player.getMinions().get(attackIndex);
            c.setHealth(c.getHealth() - 4);
            if (card.isGold()) {
                attackIndex = Main.getUniqueRandomInteger(player.getMinions().size(), new ArrayList<>(attackIndex));
                c = player.getMinions().get(attackIndex);
                c.setHealth(c.getHealth() - 4);
            }
        }
    }
}
