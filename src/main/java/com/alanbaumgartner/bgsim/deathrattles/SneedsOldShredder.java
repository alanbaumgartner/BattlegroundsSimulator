package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;

import java.util.List;

public class SneedsOldShredder extends Deathrattle {
    @Override
    public void Simulate(Card card, Player player, List<Card> cards) {
        int index = getIndex(card, player);
        player.removeCard(card);
        player.addCard(index, (Card) Main.getRandomLegendary().clone());
        if (card.isGold()) {
            player.addCard(index, (Card) Main.getRandomLegendary().clone());
        }
    }
}
