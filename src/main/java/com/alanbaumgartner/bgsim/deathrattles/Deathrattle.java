package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Deathrattle {
	protected DType type = null;
	protected Card token = null;

	public abstract List<Card> Simulate(Card card);

	public abstract List<Card> Simulate(Card card, Player player);

	public abstract void init();

	public DType getType() {
		return type;
	}

	protected List<Card> getTokens(boolean golden, Card[] cards) {
		List<Card> tokens = new ArrayList<>();
		if (golden) {
			for (Card c : cards) {
				c.setHealth(c.getHealth() * 2);
				c.setAttack(c.getAttack() * 2);
				tokens.add(c);
			}
		} else {
			tokens.addAll(Arrays.asList(cards));
		}
		return tokens;
	}

	protected int getIndex(Card card, Player player) {
		return Math.max(player.getMinions().indexOf(card), 0);
	}
}
