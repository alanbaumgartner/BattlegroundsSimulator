package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class PilotedShredder extends Deathrattle {
    static DType type = DType.SUMMON;

    @Override
    public void Simulate(Card card, Player player, List<Card> cards) {
player.removeCard(card);if (card.isGold()) {

        }
    }


}
