package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Token;

import java.util.List;

public class RatPack extends Deathrattle {


    static {
        type = DType.SUMMON;
    }

    @Override
    public List<Card> Simulate(Card card, Player owner, Player opponent, int index) {
        owner.removeCard(card);
        Card[] tokens = new Card[Math.min(card.getAttack(), 7)];
        for (int i = 0; i < card.getAttack(); i++) {
            tokens[i] = (Card) Main.Tokens.get(Token.RAT).clone();
        }
        addTokens(card.isGold(), index, tokens, owner);
        return null;
    }


}
