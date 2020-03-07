package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Token;

import java.util.List;

public class InfestedWolf extends Deathrattle {

    static {
        type = DType.SUMMON;
    }


    @Override
    public List<Card> Simulate(Card card, Player player, List<Card> cards) {
        int index = getIndex(card, player);
        player.removeCard(card);
        if (card.isGold()) {

        } else {
            player.addCard(index, (Card) Main.Tokens.get(Token.SPIDER).clone());
            player.addCard(index, (Card) Main.Tokens.get(Token.SPIDER).clone());
        }
        return null;
    }


}
