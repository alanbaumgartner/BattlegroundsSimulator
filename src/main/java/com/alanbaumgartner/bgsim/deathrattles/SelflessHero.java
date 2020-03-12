package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.ArrayList;
import java.util.List;

public class SelflessHero extends Deathrattle {

	@Override
	public void init() {

		type = DType.BUFF;
	}

	@Override
	public List<Card> Simulate(Card card) {
		return null;
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		if (player.getAliveMinions().size() > 0) {
			int shieldIndex = Main.getRandomInteger(player.getAliveMinions().size());
			Card c = player.getAliveMinions().get(shieldIndex);
			c.getMechanics().add(Mechanics.DIVINE_SHIELD);
			if (card.isGold()) {
				shieldIndex = Main.getUniqueRandomInteger(player.getAliveMinions().size(), new ArrayList<>(shieldIndex));
				c = player.getAliveMinions().get(shieldIndex);
				c.getMechanics().add(Mechanics.DIVINE_SHIELD);
			}
		}
		return null;
	}
}
