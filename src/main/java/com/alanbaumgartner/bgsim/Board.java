package com.alanbaumgartner.bgsim;

import java.util.List;

public class Board {

	private Integer step;
	private Integer playerTurn;

	private Integer[] attackingMinion;

	private Player[] players;

	public Integer score;
	public Integer winner;

	public Board(Player one, Player two) {
		score = 0;
		winner = 0;
		step = 0;
		playerTurn = Main.rand.nextInt(1);
		attackingMinion = new Integer[2];
		attackingMinion[0] = 0;
		attackingMinion[1] = 0;
		players = new Player[2];
		players[0] = one;
		players[1] = two;
	}

	public void simulate() {
		while(true) {
			if (players[0].getMinions().size() == 0) {
				winner += 2;
			}
			if (players[1].getMinions().size() == 0) {
				winner += 1;
			}
			if (winner != 0) {
				break;
			} else {
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
		}
	}

	public Card getAttackingMinion() {
		attackingMinion[playerTurn] = Math.min(players[playerTurn].getNumMinions() - 1, attackingMinion[playerTurn]);
		Card c = players[playerTurn].getMinions().get(attackingMinion[playerTurn]);
		attackingMinion[playerTurn]++;
		return c;
	}

	public Card getDefendingMinion() {
		Integer index;
		Card defender;
		List<Card> tauntMinions = players[1 - playerTurn].getTauntMinions();
		if (tauntMinions.isEmpty()) {
			index = Main.rand.nextInt(players[1 - playerTurn].getMinions().size());
			defender = players[1 - playerTurn].getMinions().get(index);
		} else {
			index = Main.rand.nextInt(tauntMinions.size());
			defender = tauntMinions.get(index);
		}
		return defender;
	}

	public void doStep() {
		Card attacker = getAttackingMinion();
		Card defender = getDefendingMinion();

		attacker.attack(defender);
//		System.out.println(attacker +" "+ defender);

		if (attacker.getDead()) {
//			handleDeathrattle(playerTurn, attacker.getDeathrattle());
			players[playerTurn].removeMinion(attacker);
		}
		if (defender.getDead()) {
//			handleDeathrattle(1 - playerTurn, defender.getDeathrattle());
			players[1 - playerTurn].removeMinion(defender);
		}
		step++;
		playerTurn = 1 - playerTurn;
	}

	private void handleDeathrattle(Integer ownerIndex, Deathrattle deathrattle) {
		if (deathrattle == null) {
			return;
		}
		switch (deathrattle.getType()) {
			case BUFF:
			case SUMMON:
				deathrattle.Simulate(players[ownerIndex].getMinions());
				break;
			case ATTACK:
				deathrattle.Simulate(players[1 - ownerIndex].getMinions());
				break;
		}
	}


}
