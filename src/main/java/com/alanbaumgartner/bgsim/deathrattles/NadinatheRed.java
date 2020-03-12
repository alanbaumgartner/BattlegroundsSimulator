package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;
import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Race;

import java.util.List;

public class NadinatheRed extends Deathrattle {


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
			for (Card c : player.getAliveMinions()) {
				if (c.getRace() == Race.DRAGON) {
					c.getMechanics().add(Mechanics.DIVINE_SHIELD);
				}
			}
		}
		return null;
	}
}
