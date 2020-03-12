package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class Ability implements PropertyChangeListener {
	protected static AType type = null;

	public AType getType() {
		return type;
	}

	public abstract void propertyChange(PropertyChangeEvent event);
}
