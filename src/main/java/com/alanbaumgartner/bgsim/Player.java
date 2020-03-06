package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.Hero;
import com.alanbaumgartner.bgsim.enums.Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private Integer health;
    private Integer tier;
    private Hero hero;
    private List<Card> minions;

    public Player(Player copy) {
        health = copy.health;
        tier = copy.tier;
        hero = copy.hero;
        minions = new ArrayList<>();
        for (Card c : copy.getMinions()) {
            minions.add((Card) c.clone());
        }
    }

    public Player(List<Card> minions) {
        this.minions = minions;
        this.health = 40;
        this.tier = 1;
        this.hero = Hero.DEATHWING;
    }

    public Integer getTier() {
        return tier;
    }

    public List<Card> getTauntMinions() {
        return minions.stream().filter(c -> c.getMechanics().contains(Mechanics.TAUNT)).collect(Collectors.toList());
    }

    public Integer getNumMinions() {
        return minions.size();
    }

    public List<Card> getMinions() {
        return minions;
    }

    public void removeMinion(Card c) {
        this.minions.remove(c);
    }


    public void heroPower() {
        switch (hero) {
            case DEATHWING:
                break;
            case NEFARIAN:
                break;
            case PATCHESTHEPIRATE:
                break;
            case RAGNAROSTHEFIRELORD:
                break;
            case PROFESSORPUTRICIDE:
                break;
            case THEGREATAKAZAMZARAK:
                break;
            case GIANTFIN:
                break;
            default:
                break;
        }
    }
}