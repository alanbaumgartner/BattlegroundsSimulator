package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.List;

public class BronzeWarden extends Deathrattle {

    static {
        type = DType.SUMMON;
    }

    @Override
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        if (card.hasReborn()) {
            owner.removeCard(card);
        } else {
            if (card.isGold()) {
                card.setAttack(4);
            } else {
                card.setAttack(2);
            }
            card.setHealth(1);
            card.getMechanics().add(Mechanics.DIVINE_SHIELD);
            card.setReborn(true);
        }
        return null;
    }
}
