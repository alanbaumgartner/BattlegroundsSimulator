package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.deathrattles.Deathrattle;
import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.handlers.AbilityHandler;
import com.alanbaumgartner.bgsim.handlers.DeathrattleHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {

    public int score = 0;
    public int winner = 0;
    private int step = 0;
    private int playerTurn;
    private int timeoutCounter = 0;

    private Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");

    private Player[] players = new Player[2];

    private DeathrattleHandler deathrattleHandler;
    private AbilityHandler abilityHandler;

    public Board(Player one, Player two) {
        // The player with the most minions is first, otherwise random.
        int p1Minions = one.getNumMinions();
        int p2Minions = two.getNumMinions();
        if (p1Minions == p2Minions) {
            playerTurn = Main.getRandomInteger(1);
        } else {
            playerTurn = p1Minions > p2Minions ? 0 : 1;
        }
        players[0] = one;
        players[1] = two;

        deathrattleHandler = new DeathrattleHandler(one, two);
        abilityHandler = new AbilityHandler(one, two);
    }

    public void simulate() {
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
        Card attacker = players[playerTurn].getNextAttacker();
        Card defender = getDefendingMinion();
        attacker.attack(defender);
        if (attacker.isDead()) {
            handleDeathrattle(attacker);
        }
        if (defender.isDead()) {
            handleDeathrattle(defender);
        }
        step++;
        playerTurn = 1 - playerTurn;
    }

    private void startPhase() {

    }

    private void attackPhase() {

    }

    private int deathPhase() {
        int deaths = 0;
        for (Card c : players[0].getMinions()) {
            if (c.isDead()) {

                deaths++;
            }

        }
        for (Card c : players[1].getMinions()) {
            if (c.isDead()) {

                deaths++;
            }
        }
        return deaths;
    }

    private void handleDeathrattle(Card card) {
        timeoutCounter = 0;
        if (!card.getMechanics().contains(Mechanics.DEATHRATTLE)) {
            players[card.getPlayer()].removeCard(card);
            return;
        }
        String name = card.getName();
        Matcher matcher = pattern.matcher(name);
        String s = matcher.replaceAll("");
        Deathrattle dr = Main.deathrattleMap.get(s);
        List<Card> killed = null;
        switch (dr.getType()) {
            case BUFF:
            case SUMMON:
                killed = dr.Simulate(card, players[card.getPlayer()], players[card.getPlayer()].getMinions());
                break;
            case ATTACK:
                killed = dr.Simulate(card, players[card.getPlayer()], players[1 - card.getPlayer()].getMinions());
                break;
        }
        if (killed != null && !killed.isEmpty()) {
            for (Card c : killed) {
                handleDeathrattle(c);
            }
        }
    }

}
