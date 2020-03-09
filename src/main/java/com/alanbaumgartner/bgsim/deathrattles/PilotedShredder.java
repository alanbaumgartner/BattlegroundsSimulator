package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class PilotedShredder extends Deathrattle {

    static {
        type = DType.SUMMON;
    }


    @Override
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        owner.removeCard(card);
        owner.addCard(index, (Card) Main.getRandomTwoCost().clone());
        if (card.isGold()) {
            owner.addCard(index, (Card) Main.getRandomTwoCost().clone());
        }
        return null;
    }


}
