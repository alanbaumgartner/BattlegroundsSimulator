package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.ArrayList;
import java.util.List;

public class SelflessHero extends Deathrattle {


    static {
        type = DType.BUFF;
    }


    @Override
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        owner.removeCard(card);
        if (owner.getMinions().size() <= 0) {
            return null;
        }
        int shieldIndex = Main.getRandomInteger(owner.getMinions().size());
        Card c = owner.getMinions().get(shieldIndex);
        c.getMechanics().add(Mechanics.DIVINE_SHIELD);
        if (card.isGold()) {
            shieldIndex = Main.getUniqueRandomInteger(owner.getMinions().size(), new ArrayList<>(shieldIndex));
            c = owner.getMinions().get(shieldIndex);
            c.getMechanics().add(Mechanics.DIVINE_SHIELD);
        }
        return null;
    }


}
