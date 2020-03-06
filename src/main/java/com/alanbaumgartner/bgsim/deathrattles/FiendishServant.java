package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class FiendishServant extends Deathrattle {
    static DType type = DType.BUFF;

    @Override
    public void Simulate(Card card, List<Card> cards) {
        Integer index = Main.rand.nextInt(cards.size());
        Card c = cards.get(index);
        c.setAttack(c.getAttack() + card.getAttack());
        if (card.isGold()) {
            index = Main.getUniqueRandomInteger(cards.size(), new ArrayList<>(index));
            c = cards.get(index);
            c.setAttack(c.getAttack() + card.getAttack());
        }
    }
}
