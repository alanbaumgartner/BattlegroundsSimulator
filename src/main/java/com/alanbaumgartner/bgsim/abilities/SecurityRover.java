package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

import java.beans.PropertyChangeEvent;

public class SecurityRover extends Ability {
	public void init() {
		type = AType.DEATH;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

	}
}

