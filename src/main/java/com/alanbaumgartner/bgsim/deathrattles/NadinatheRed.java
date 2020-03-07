package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Race;

import java.util.List;

public class NadinatheRed extends Deathrattle {


    static {
        type = DType.BUFF;
    }


    @Override
    public List<Card> Simulate(Card card, Player player, List<Card> cards) {
        player.removeCard(card);
        if (cards.size() <= 0) {
            return null;
        }
        for (Card c : player.getMinions()) {
            if (c.getRace() == Race.DRAGON) {
                c.getMechanics().add(Mechanics.DIVINE_SHIELD);
            }
        }
        return null;
    }


}
