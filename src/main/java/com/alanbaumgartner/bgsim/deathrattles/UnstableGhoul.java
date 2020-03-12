package com.alanbaumgartner.bgsim.deathrattles;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.enums.DType;

import java.util.List;

public class UnstableGhoul extends Deathrattle {

	@Override
	public void init() {
		type = DType.ATTACK;
	}

	@Override
	public List<Card> Simulate(Card card) {
		return null;
	}

	@Override
	public List<Card> Simulate(Card card, Player player) {
//        List<Card> killed = new ArrayList<>();
		card.getPlayer().getAliveMinions().forEach(c -> c.setHealth(c.getHealth() - 1));
		player.getAliveMinions().forEach(c -> c.setHealth(c.getHealth() - 1));
		return null;
//        return killed;
//        for (Card c : card.getPlayer().getMinions()) {
//            c.setHealth(c.getHealth() - 1);
//            System.out.println(c.getHealth());
//        }
//        for (Card c : player.getMinions().toArray()) {
//            c.setHealth(0);
//            System.out.println(c.getHealth());
//        }
	}
}
