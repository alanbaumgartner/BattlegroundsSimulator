package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

import java.beans.PropertyChangeEvent;

public class ImpGangBoss extends Ability {
	public void init() {
		type = AType.DAMAGE;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

	}
}

