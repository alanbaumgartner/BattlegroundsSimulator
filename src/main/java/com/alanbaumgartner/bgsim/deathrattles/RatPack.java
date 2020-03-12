package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Token;

import java.util.List;

public class RatPack extends Deathrattle {


	public void init() {
		token = (Card) Main.Tokens.get(Token.RAT).clone();
		type = DType.SUMMON;
	}

	@Override
	public List<Card> Simulate(Card card) {
		Card[] tokens = new Card[Math.min(card.getAttack(), 7)];
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = (Card) Main.Tokens.get(Token.RAT).clone();
		}
		return getTokens(card.isGold(), tokens);
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		return null;
	}
}
