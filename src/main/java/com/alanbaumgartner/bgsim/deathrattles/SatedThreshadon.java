package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Token;

import java.util.List;

public class SatedThreshadon extends Deathrattle {


	public void init() {
		token = Main.Tokens.get(Token.PLANT).clone();
		type = DType.SUMMON;
	}


	@Override
	public List<Card> Simulate(Card card) {
		Card[] tokens = {token.clone(), token.clone(), token.clone()};
		return getTokens(card.isGold(), tokens);
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		return null;
	}
}
