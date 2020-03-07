package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.ArrayList;
import java.util.List;

public class SelflessHero extends Deathrattle {
    static DType type = DType.BUFF;

    @Override
    public void Simulate(Card card, Player player, List<Card> cards) {
        player.removeCard(card);
        if (cards.size() <= 0) {
            return;
        }
        int index = Main.getRandomInteger(cards.size());
        Card c = cards.get(index);
        c.getMechanics().add(Mechanics.DIVINE_SHIELD);
        if (card.isGold()) {
            index = Main.getUniqueRandomInteger(cards.size(), new ArrayList<>(index));
            c = cards.get(index);
            c.getMechanics().add(Mechanics.DIVINE_SHIELD);
        }
    }


}
