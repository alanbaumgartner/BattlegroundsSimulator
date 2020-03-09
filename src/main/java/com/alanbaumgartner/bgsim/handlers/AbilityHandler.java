package com.alanbaumgartner.bgsim.handlers;

import com.alanbaumgartner.bgsim.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AbilityHandler implements PropertyChangeListener {

    Player one;
    Player two;

    public AbilityHandler(Player one, Player two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
