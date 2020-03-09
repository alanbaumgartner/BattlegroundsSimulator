package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

import java.beans.PropertyChangeEvent;

public class PackLeader extends Ability {
    static {
        type = AType.DEATH;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {

    }
}

