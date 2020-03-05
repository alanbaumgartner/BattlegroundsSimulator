package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.enums.Mechanics;
import com.alanbaumgartner.bgsim.enums.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

	private Integer health;
	private Hero hero;
	private List<Card> minions;

	public Player(List<Card> minions) {
		this.minions = minions;
		health = 40;
		hero = Hero.DEATHWING;
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
		switch(hero) {
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