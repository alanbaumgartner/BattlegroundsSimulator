package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class PilotedShredder extends Deathrattle {
	@Override
	public void init() {

		type = DType.SUMMON;
	}


	@Override
	public List<Card> Simulate(Card card) {
		List<Card> summons = new ArrayList<>();
		summons.add(Main.getRandomTwoCost());
		if (card.isGold()) {
			summons.add(Main.getRandomTwoCost());
		}
		return summons;


	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		return null;
	}
}
