package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

import java.beans.PropertyChangeEvent;

public class ZappSlywick extends Ability {
	public void init() {
		type = AType.PASSIVE;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

	}
}


