package com.alanbaumgartner.bgsim.handlers;

import com.alanbaumgartner.bgsim.Player;

public abstract class Handler {

    Player one;
    Player two;

    public Handler(Player one, Player two) {
        this.one = one;
        this.two = two;
    }
}
