package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class TortollanShellraiser extends Deathrattle {
    static DType type = DType.BUFF;

    @Override
    public void Simulate(Card card, Player player, List<Card> cards) {
        player.removeCard(card);
        if (card.isGold()) {

        } else {

        }
    }


}
