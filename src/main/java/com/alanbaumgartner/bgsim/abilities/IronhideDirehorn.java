package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

import java.beans.PropertyChangeEvent;

public class IronhideDirehorn extends Ability {
	public void init() {
		type = AType.OVERKILL;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

	}
}

