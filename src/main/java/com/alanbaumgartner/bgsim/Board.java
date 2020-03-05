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



		players = new Player[2];
		players[0] = one;
		players[1] = two;
	}

	public void simulate() {
		Integer score = 0;
		Integer winner = 0;
		while(true) {
			if (players[0].getMinions().size() == 0) {
				winner = 1;
			}
			if (players[1].getMinions().size() == 0) {
				winner = 2;
			}
		}


	}

	public void doStep() {
		Integer attackerIndex = playerTurn;
		Integer defenderIndex = 1 - playerTurn;


		attackingMinion[attackerIndex] = Math.min(players[attackerIndex].getNumMinions(), attackingMinion[attackerIndex]);

		List<Card> tauntMinions = players[defenderIndex].getTauntMinions();

		Card attacker = players[attackerIndex].getMinions().get(attackingMinion[attackerIndex]);
		Card defender;
		if (tauntMinions.isEmpty()) {
			defender = players[defenderIndex].getMinions().get(Main.rand.nextInt(players[defenderIndex].getMinions().size()));
		} else {
			defender = tauntMinions.get(Main.rand.nextInt(tauntMinions.size()));
		}
		if (attacker.getDead()) {
			HandleDeathrattle(playerTurn, attacker.getDeathrattle());
		}
		if (defender.getDead()) {
			HandleDeathrattle(1 - playerTurn, defender.getDeathrattle());
		}
		step++;
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
