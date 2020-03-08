package com.alanbaumgartner.bgsim.abilities;

import com.alanbaumgartner.bgsim.enums.AType;

public abstract class Ability {
    private AType type = null;

    public AType getType() {
        return type;
    }
}
