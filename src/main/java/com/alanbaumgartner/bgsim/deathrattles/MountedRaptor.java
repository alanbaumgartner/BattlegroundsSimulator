package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class MountedRaptor extends Deathrattle {

    @Override
    public void init() {

        type = DType.SUMMON;
    }


    @Override
    public List<Card> Simulate(Card card) {
        if (card.isGold()) {

        } else {

        }
        return null;
    }

    @Override
    public void Simulate(Card card, Player player) {

    }
}
