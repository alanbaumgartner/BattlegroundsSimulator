package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Token;

import java.util.ArrayList;
import java.util.List;

public class BronzeWarden extends Deathrattle {

	@Override
	public List<Card> Simulate(Card card) {
		List<Card> summons = new ArrayList<>();
		Card c = token.clone();
		if (!card.hasReborn()) {
			if (card.isGold()) {
				c.setAttack(4);
			} else {
				c.setAttack(2);
			}
			c.setHealth(1);
			c.setReborn(true);
		}
		return summons;
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		return null;
	}

	@Override
	public void init() {
		token = Main.Tokens.get(Token.BRONZEWARDEN).clone();
		type = DType.SUMMON;
	}
}
