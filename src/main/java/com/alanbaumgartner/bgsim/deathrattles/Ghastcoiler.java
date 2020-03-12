package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class Ghastcoiler extends Deathrattle {

	@Override
	public List<Card> Simulate(Card card) {
		List<Card> summons = new ArrayList<>();
		summons.add((Card) Main.getRandomDeathrattle().clone());
		summons.add((Card) Main.getRandomDeathrattle().clone());
		if (card.isGold()) {
			summons.add((Card) Main.getRandomDeathrattle().clone());
			summons.add((Card) Main.getRandomDeathrattle().clone());
		}
		return summons;
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		return null;
	}

	@Override
	public void init() {
		type = DType.SUMMON;
	}
}
