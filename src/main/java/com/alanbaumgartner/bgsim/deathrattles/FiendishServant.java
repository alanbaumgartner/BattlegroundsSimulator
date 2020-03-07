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
    public List<Card> Simulate(Card card, Player player, List<Card> cards) {
        player.removeCard(card);
        if (cards.size() <= 0) {
            return null;
        }
        int index = Main.getRandomInteger(cards.size());
        Card c = cards.get(index);
        c.setAttack(c.getAttack() + card.getAttack());
        if (card.isGold()) {
            index = Main.getUniqueRandomInteger(cards.size(), new ArrayList<>(index));
            c = cards.get(index);
            c.setAttack(c.getAttack() + card.getAttack());
        }
        return null;
    }
}
