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
        Integer index = Math.max(player.getMinions().indexOf(card), 0);
        player.removeCard(card);
        player.addCard(index, Main.getRandomDeathrattle());
        player.addCard(index, Main.getRandomDeathrattle());
        if (card.isGold()) {
            player.addCard(index, Main.getRandomDeathrattle());
            player.addCard(index, Main.getRandomDeathrattle());
        }
    }


}
