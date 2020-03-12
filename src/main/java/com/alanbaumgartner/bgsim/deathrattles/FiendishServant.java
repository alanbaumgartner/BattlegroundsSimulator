package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class FiendishServant extends Deathrattle {

    @Override
    public List<Card> Simulate(Card card) {
        return null;
    }

    @Override
    public void Simulate(Card card, Player player) {
        if (player.getMinions().size() <= 0) {
            return;
        }
        int index = Main.getRandomInteger(player.getNumMinions());
        Card c = player.getMinions().get(index);
        c.setAttack(c.getAttack() + card.getAttack());
        if (card.isGold()) {
            index = Main.getUniqueRandomInteger(player.getMinions().size(), new ArrayList<>(index));
            c = player.getMinions().get(index);
            c.setAttack(c.getAttack() + card.getAttack());
        }
    }

    @Override
    public void init() {
        type = DType.BUFF;
    }
}
