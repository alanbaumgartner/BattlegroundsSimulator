package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;

import java.util.List;

public class UnstableGhoul extends Deathrattle {
    @Override
    public void Simulate(Card card, Player player, List<Card> cards) {
        player.removeCard(card);
    }
}
