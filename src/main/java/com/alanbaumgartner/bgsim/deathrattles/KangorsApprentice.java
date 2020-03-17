package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class KangorsApprentice extends Deathrattle {

	@Override
	public void init() {
		type = DType.SUMMON;
	}

	@Override
	public List<Card> Simulate(Card card) {
		List<Card> summons = new ArrayList<>();
		Player player = card.getPlayer();
		player.deadMechs.forEach(c -> {
			String name = c.getName();
			if (c.isGold()) {
				summons.add(Main.getCard(name, Main.GoldMinions));
			} else {
				summons.add(Main.getCard(name, Main.Minions));
			}
		});
		if (card.isGold()) {
			player.deadMechs.forEach(c -> {
				String name = c.getName();
				if (c.isGold()) {
					summons.add(Main.getCard(name, Main.GoldMinions));
				} else {
					summons.add(Main.getCard(name, Main.Minions));
				}
			});
		}
		return summons;
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		return null;
	}
}
