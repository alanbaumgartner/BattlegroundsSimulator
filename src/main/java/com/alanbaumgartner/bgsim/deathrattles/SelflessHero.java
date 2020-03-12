package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.ArrayList;
import java.util.List;

public class SelflessHero extends Deathrattle {

    @Override
    public void init() {

        type = DType.BUFF;
    }

    @Override
    public List<Card> Simulate(Card card) {
        return null;
    }

    @Override
    public void Simulate(Card card, Player player) {
        if (player.getMinions().size() > 0) {
            int shieldIndex = Main.getRandomInteger(player.getMinions().size());
            Card c = player.getMinions().get(shieldIndex);
            c.getMechanics().add(Mechanics.DIVINE_SHIELD);
            if (card.isGold()) {
                shieldIndex = Main.getUniqueRandomInteger(player.getMinions().size(), new ArrayList<>(shieldIndex));
                c = player.getMinions().get(shieldIndex);
                c.getMechanics().add(Mechanics.DIVINE_SHIELD);
            }
        }
    }
}
