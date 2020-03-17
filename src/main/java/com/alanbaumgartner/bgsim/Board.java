package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.handlers.AbilityHandler;
import com.alanbaumgartner.bgsim.handlers.DeathrattleHandler;

import java.util.Iterator;
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
		printState("Start");
		attackPhase();
		if (deathPhase() > 0) {
			timeoutCounter = 0;
		}
		printState("End");
		step++;
		playerTurn = 1 - playerTurn;
	}

	private void startPhase() {

	}

	private void attackPhase() {
		Card attacker = players[playerTurn].getNextAttacker();
		if (attacker == null) {
			return;
		}
		Card defender = getDefendingMinion();
		attacker.attack(defender);
	}

	private int deathPhase() {
		players[0].getMinions().removeAll(players[0].getDeadMinions());
		players[1].getMinions().removeAll(players[1].getDeadMinions());
		return deathrattleHandler.getDeadThisTurn();
	}

	public void printState(String soe) {
		String leftAlignFormat = "| %-20s | %-6s | %-6s | %-20s | %-6s | %-6s |%n";
		String roundFormat = "| %-79s |%n";
		System.out.format("+---------------------------------------------------------------------------------+%n");
		System.out.format(roundFormat, "Round " + step +  " " +soe);
		System.out.format("+----------------------------------------+----------------------------------------+%n");
		System.out.format("| Player One                             | Player Two                             |%n");
		System.out.format("+----------------------+--------+--------+----------------------+--------+--------+%n");
		System.out.format("| Minion               | Attack | Health | Minion               | Attack | Health |%n");
		System.out.format("+----------------------+--------+--------+----------------------+--------+--------+%n");

		Iterator<Card> iter1 = players[0].getAliveMinions().iterator();
		Iterator<Card> iter2 = players[1].getAliveMinions().iterator();

		while (iter1.hasNext() || iter2.hasNext()) {
			if (iter1.hasNext() && iter2.hasNext()) {
				Card c1 = iter1.next();
				Card c2 = iter2.next();
				System.out.format(leftAlignFormat, c1.getName(), c1.getAttack(), c1.getHealth(), c2.getName(), c2.getAttack(), c2.getHealth());
			} else if (iter1.hasNext()) {
				Card c1 = iter1.next();
				System.out.format(leftAlignFormat, c1.getName(), c1.getAttack(), c1.getHealth(), "", "", "");
			} else {
				Card c2 = iter2.next();
				System.out.format(leftAlignFormat, "", "", "", c2.getName(), c2.getAttack(), c2.getHealth());
			}
		}
		System.out.format("+----------------------+--------+--------+----------------------+--------+--------+%n");
	}

	@Override
	public void run() {
		simulate();
	}
}
