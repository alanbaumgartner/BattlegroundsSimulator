package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.ArrayList;
import java.util.List;

public class FiendishServant extends Deathrattle {

	@Override
	public List<Card> Simulate(Card card) {
		return null;
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
		if (player.getAliveMinions().size() <= 0) {
			return null;
		}
		int index = Main.getRandomInteger(player.getAliveMinions().size());
		Card c = player.getAliveMinions().get(index);
		c.setAttack(c.getAttack() + card.getAttack());
		if (card.isGold()) {
			index = Main.getUniqueRandomInteger(player.getAliveMinions().size(), new ArrayList<>(index));
			c = player.getAliveMinions().get(index);
			c.setAttack(c.getAttack() + card.getAttack());
		}
		return null;
	}

	@Override
	public void init() {
		type = DType.BUFF;
	}
}
