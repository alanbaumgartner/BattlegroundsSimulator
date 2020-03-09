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
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        owner.removeCard(card);
        if (owner.getMinions().size() <= 0) {
            return null;
        }
        for (Card c : owner.getMinions()) {
            if (c.getRace() == Race.DRAGON) {
                c.getMechanics().add(Mechanics.DIVINE_SHIELD);
            }
        }
        return null;
    }


}
