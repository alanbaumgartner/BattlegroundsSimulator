package com.alanbaumgartner.bgsim;

import com.alanbaumgartner.bgsim.factory.Card;
import com.alanbaumgartner.bgsim.enums.Hero;

public class Player {

	private Integer health;
	private Hero hero;
	private Card[] minions;

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