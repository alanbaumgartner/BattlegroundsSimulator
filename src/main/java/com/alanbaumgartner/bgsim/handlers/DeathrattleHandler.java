package com.alanbaumgartner.bgsim.handlers;

import com.alanbaumgartner.bgsim.Card;
import com.alanbaumgartner.bgsim.Main;
import com.alanbaumgartner.bgsim.Player;
import com.alanbaumgartner.bgsim.deathrattles.Deathrattle;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DeathrattleHandler extends Handler {

	private int deadThisTurn = 0;
	private Queue<Card> drq = new ConcurrentLinkedQueue<>();

	public DeathrattleHandler(Player one, Player two) {
		super(one, two);
	}

	public int getDeadThisTurn() {
		int ret = deadThisTurn;
		deadThisTurn = 0;
		return ret;
	}

	@Override
	public void update(Card c) {
		deadThisTurn++;
		int index = c.getPlayer().indexOfCard(c);
		Player player = c.getPlayer();
		Player opponent = player.equals(one) ? two : one;

		if (!c.getMechanics().contains(Mechanics.DEATHRATTLE)) {
			return;
		}

		Deathrattle dr = Main.getDeathrattle(c);
		List<Card> summons = null;
		switch (dr.getType()) {
			case BUFF:
				dr.Simulate(c, player);
				break;
			case SUMMON:
				summons = dr.Simulate(c);
				break;
			case ATTACK:
				dr.Simulate(c, opponent);
				break;
		}

		if (summons != null) {
			for (Card card : summons) {
				card.setDeathrattleHandler(this);
				player.addCard(index, card);
			}
		}
	}
}
