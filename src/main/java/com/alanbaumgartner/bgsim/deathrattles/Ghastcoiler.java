package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class Ghastcoiler extends Deathrattle {
    public DType type = DType.SUMMON;

    @Override
    public void Simulate(Card card, Player player, List<Card> cards) {
        int index = getIndex(card, player);
        player.removeCard(card);
        player.addCard(index, (Card) Main.getRandomDeathrattle().clone());
        player.addCard(index, (Card) Main.getRandomDeathrattle().clone());
        if (card.isGold()) {
            player.addCard(index, (Card) Main.getRandomDeathrattle().clone());
            player.addCard(index, (Card) Main.getRandomDeathrattle().clone());
        }
    }


}
