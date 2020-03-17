package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Token;

import java.util.List;


public class HarvestGolem extends Deathrattle {

	public void init() {
		type = DType.SUMMON;
		token = Main.Tokens.get(Token.DAMAGEDGOLEM);
	}

	@Override
	public List<Card> Simulate(Card card) {
		Card[] tokens = {token.clone()};
		return getTokens(card.isGold(), tokens);
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		return null;
	}


}
