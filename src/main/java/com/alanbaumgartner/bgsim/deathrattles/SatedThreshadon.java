package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Token;

import java.util.List;

public class SatedThreshadon extends Deathrattle {


    static {
        type = DType.SUMMON;
    }


    @Override
    public List<Card> Simulate(Card card, Player player, List<Card> cards) {
        int index = getIndex(card, player);
        player.removeCard(card);
        Card[] tokens = {(Card) Main.Tokens.get(Token.PLANT).clone(), (Card) Main.Tokens.get(Token.PLANT).clone(), (Card) Main.Tokens.get(Token.PLANT).clone()};
        addTokens(card.isGold(), index, tokens, player);
        return null;
    }


}
