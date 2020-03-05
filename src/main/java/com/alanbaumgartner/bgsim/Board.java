package com.alanbaumgartner.bgsim;

import java.util.List;

public class Board {

	private Integer step;
	private Integer playerTurn;

	private Integer[] attackingMinion;

	private Player[] players;

	public Board(Player one, Player two) {
		step = 0;
		playerTurn = 0;
		attackingMinion = new Integer[2];
		attackingMinion[0] = 0;
		attackingMinion[1] = 0;
		players = new Player[2];
		players[0] = one;
		players[1] = two;
	}

	public void simulate() {
		Integer score = 0;
		Integer winner = 0;
		while(true) {
			doStep();
			if (players[0].getMinions().size() == 0) {
				winner += 2;
			}
			if (players[1].getMinions().size() == 0) {
				winner += 1;
			}
			if (winner != 0) {
				break;
			}
		}
		switch (winner) {
			case 1:
				for (Card c : players[0].getMinions()) {
					score += c.getTechLevel();
				}
				break;
			case 2:
				for (Card c : players[1].getMinions()) {
					score += c.getTechLevel();
				}
				break;
		}
		System.out.println(score + " " + winner);
	}

	public Card getAttackingMinion() {
		attackingMinion[playerTurn] = Math.min(players[playerTurn].getNumMinions(), attackingMinion[playerTurn]);
		return players[playerTurn].getMinions().get(attackingMinion[playerTurn]);
	}

	public Card getDefendingMinion() {
		Card defender;
		List<Card> tauntMinions = players[1 - playerTurn].getTauntMinions();
		if (tauntMinions.isEmpty()) {
			defender = players[1 - playerTurn].getMinions().get(Main.rand.nextInt(players[1 - playerTurn].getMinions().size()));
		} else {
			defender = tauntMinions.get(Main.rand.nextInt(tauntMinions.size()));
		}
		return defender;
	}

	public void doStep() {
		Card attacker = getAttackingMinion();
		Card defender = getDefendingMinion();

		attacker.Attack(defender);

		if (attacker.getDead()) {
			HandleDeathrattle(playerTurn, attacker.getDeathrattle());
			players[playerTurn].removeMinion(attacker);
		}
		if (defender.getDead()) {
			HandleDeathrattle(1 - playerTurn, defender.getDeathrattle());
			players[1 - playerTurn].removeMinion(defender);
		}
		step++;
		playerTurn = 1 - playerTurn;
	}

	private void HandleDeathrattle(Integer ownerIndex, Deathrattle deathrattle) {
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
