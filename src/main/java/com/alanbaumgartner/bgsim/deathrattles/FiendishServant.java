package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class FiendishServant extends Deathrattle {

    static {
        type = DType.BUFF;
    }

    @Override
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        owner.removeCard(card);
        if (owner.getMinions().size() <= 0) {
            return null;
        }
        Card c = owner.getMinions().get(index);
        c.setAttack(c.getAttack() + card.getAttack());
        if (card.isGold()) {
            index = Main.getUniqueRandomInteger(owner.getMinions().size(), new ArrayList<>(index));
            c = owner.getMinions().get(index);
            c.setAttack(c.getAttack() + card.getAttack());
        }
        return null;
    }
}
