package com.alanbaumgartner.bgsim;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private Player one;
    private Player two;

    private Integer[] wins;
    private List<Integer> scores;

    private Integer iterations;


    public Simulation(Player one, Player two, Integer iterations) {
        this.one = one;
        this.two = two;
        this.iterations = iterations;
        wins = new Integer[]{0, 0, 0};
        scores = new ArrayList<>();
    }

    public void simulate() {
        for (int i = 0; i < iterations; i++) {
            Board b = new Board(new Player(one), new Player(two));
            b.simulate();
            switch (b.winner) {
                case 1:
                    wins[0]++;
                    scores.add(b.score);
                    break;
                case 2:
                    wins[1]++;
                    scores.add(-b.score);
                    break;
                case 3:
                    wins[2]++;
                    scores.add(0);
                    break;
            }
        }
        System.out.println(winPercent());
        System.out.println(tiePercent());
        System.out.println(losePercent());
        System.out.println(averageScore());

    }

    public Double averageScore() {
        Double d = 0.0;
        for (Integer i : scores) {
            d += i;
        }
        return d / iterations;
    }

    public Double winPercent() {
        return (wins[0].doubleValue() / iterations.doubleValue()) * 100;
    }

    public Double tiePercent() {
        return (wins[2].doubleValue() / iterations.doubleValue()) * 100;
    }

    public Double losePercent() {
        return (wins[1].doubleValue() / iterations.doubleValue()) * 100;
    }


}
