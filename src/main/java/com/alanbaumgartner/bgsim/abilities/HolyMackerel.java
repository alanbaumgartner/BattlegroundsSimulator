package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

import java.beans.PropertyChangeEvent;

public class HolyMackerel extends Ability {
    static {
        type = AType.DAMAGE;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {

    }
}
