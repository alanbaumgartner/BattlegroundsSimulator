package com.alanbaumgartner.bgsim;

import java.util.*;

public class Board {

    public Integer score = 0;
    public Integer winner = 0;
    private Integer step = 0;
    private Integer playerTurn;

    private Deque<Card>[] attackQueues = new Deque[2];
    private Player[] players = new Player[2];

    public Board(Player one, Player two) {
        // The player with the most minions is first, otherwise random.
        Integer p1Minions = one.getNumMinions();
        Integer p2Minions = two.getNumMinions();
        if (p1Minions.equals(p2Minions)) {
            playerTurn = Main.getRandomInteger(1);
        } else {
            playerTurn = p1Minions > p2Minions ? 0 : 1;
        }
        players[0] = one;
        players[1] = two;
        attackQueues[0] = new LinkedList<>(one.getMinions());
        attackQueues[1] = new LinkedList<>(two.getMinions());
    }

    public void simulate() {
        while (true) {
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
        Card attacker = attackQueues[playerTurn].remove();
        attackQueues[playerTurn].add(attacker);
        return attacker;
    }

    public Card getDefendingMinion() {
        Integer index;
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
        Card attacker = getAttackingMinion();
        Card defender = getDefendingMinion();

//        System.out.println("Step: " + step);
//        System.out.println("Attack: " + attacker.getName());
//        System.out.println("Defender: " + defender.getName() + "\n");

        attacker.attack(defender);

        if (attacker.isDead()) {
            attackQueues[playerTurn].remove(attacker);
            players[playerTurn].removeMinion(attacker);
			handleDeathrattle(playerTurn, attacker);
        }
        if (defender.isDead()) {
            attackQueues[1 - playerTurn].remove(defender);
            players[1 - playerTurn].removeMinion(defender);
			handleDeathrattle(1 - playerTurn, defender);
        }
        step++;
        playerTurn = 1 - playerTurn;
    }

    private void handleDeathrattle(Integer ownerIndex, Card card) {
        if (card.getDeathrattle() == null) {
            return;
        }
        switch (card.getDeathrattle().getType()) {
            case BUFF:
            case SUMMON:
                card.getDeathrattle().Simulate(card, players[ownerIndex].getMinions());
                break;
            case ATTACK:
                card.getDeathrattle().Simulate(card, players[1 - ownerIndex].getMinions());
                break;
        }
    }


}
