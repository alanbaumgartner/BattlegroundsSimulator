package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.factory.Card;

import java.util.List;

public class Board {

	private Integer turnNumber;
	private Integer playerTurn;

	private Integer[] attackingMinion;

	private Player[] players;

	public Board(Player one, Player two) {
		turnNumber = 0;
		playerTurn = 0;
		attackingMinion = new Integer[2];



		players = new Player[2];
		players[0] = one;
		players[1] = two;
	}

	public void simulate() {

	}

	public void takeTurn() {
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

	}


}
