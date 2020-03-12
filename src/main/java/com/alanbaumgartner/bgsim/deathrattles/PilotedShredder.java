package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class PilotedShredder extends Deathrattle {
    @Override
    public void init() {

        type = DType.SUMMON;
    }


    @Override
    public List<Card> Simulate(Card card) {
        List<Card> summons = new ArrayList<>();
        summons.add((Card) Main.getRandomTwoCost().clone());
        if (card.isGold()) {
            summons.add((Card) Main.getRandomTwoCost().clone());
        }
        return summons;


    }

    @Override
    public void Simulate(Card card, Player player) {

    }
}
