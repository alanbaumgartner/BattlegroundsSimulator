package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.handlers.AbilityHandler;
import com.alanbaumgartner.bgsim.handlers.DeathrattleHandler;

import java.util.List;

public class Board implements Runnable {

	public int score = 0;
	public int winner = 0;
	private int step = 0;
	private int playerTurn;
	private int timeoutCounter = 0;

	private Player[] players = new Player[2];

	private DeathrattleHandler deathrattleHandler;
	private AbilityHandler abilityHandler;

	public Board(Player one, Player two) {
		// The player with the most minions is first, otherwise random.
		Integer p1Minions = one.getNumMinions();
		Integer p2Minions = two.getNumMinions();
		if (p1Minions.equals(p2Minions)) {
			playerTurn = Main.getRandomInteger(2);
		} else {
			playerTurn = p1Minions > p2Minions ? 0 : 1;
		}
		players[0] = one;
		players[1] = two;
		deathrattleHandler = new DeathrattleHandler(one, two);
		abilityHandler = new AbilityHandler(one, two);
		for (Card c : one.getMinions()) {
			c.setDeathrattleHandler(deathrattleHandler);
			c.setAbilityHandler(abilityHandler);
		}
		for (Card c : two.getMinions()) {
			c.setDeathrattleHandler(deathrattleHandler);
			c.setAbilityHandler(abilityHandler);
		}
	}

	public void simulate() {
		startPhase();
		while (timeoutCounter <= 10) {
			if (players[0].getMinions().size() == 0) {
				winner += 2;
			}
			if (players[1].getMinions().size() == 0) {
				winner += 1;
			}
			if (winner != 0) {
				break;
			} else {
				timeoutCounter++;
				doStep();
			}
		}
		switch (winner) {
			case 1:
				score += players[0].getTier();
				for (Card c : players[0].getMinions()) {
					score += c.getTechLevel();
				}
				break;
			case 2:
				score += players[1].getTier();
				for (Card c : players[1].getMinions()) {
					score += c.getTechLevel();
				}
				break;
			case 3:
				winner = 0;
				break;
		}
	}

	public Card getDefendingMinion() {
		int index;
		Card defender;
		List<Card> tauntMinions = players[1 - playerTurn].getTauntMinions();
		if (tauntMinions.isEmpty()) {
			index = Main.getRandomInteger(players[1 - playerTurn].getMinions().size());
			defender = players[1 - playerTurn].getMinions().get(index);
		} else {
			index = Main.getRandomInteger(tauntMinions.size());
			defender = tauntMinions.get(index);
		}
		return defender;
	}

	public void doStep() {
		attackPhase();
		if (deathPhase() > 0) {
			timeoutCounter = 0;
		}
		step++;
		playerTurn = 1 - playerTurn;
	}

	private void startPhase() {

	}

	private void attackPhase() {
		Card attacker = players[playerTurn].getNextAttacker();
		Card defender = getDefendingMinion();
		attacker.attack(defender);
	}

	private int deathPhase() {
		players[0].getMinions().removeAll(players[0].getDeadMinions());
		players[1].getMinions().removeAll(players[1].getDeadMinions());
		return deathrattleHandler.getDeadThisTurn();
	}

	@Override
	public void run() {
		simulate();
	}
}
