package com.alanbaumgartner.bgsim;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Simulation {

    private Player one;
    private Player two;

    private Integer[] wins;

    private Integer iterations;

    private DescriptiveStatistics stats = new DescriptiveStatistics();

    public Simulation(Player one, Player two, int iterations) {
        this.one = one;
        this.two = two;
        this.iterations = iterations;
        wins = new Integer[]{0, 0, 0};
    }

    public void simulate() {
        for (int i = 0; i < iterations; i++) {
            Board b = new Board(new Player(one, 0), new Player(two, 1));
            b.simulate();
//            Thread object = new Thread(b);
//            object.start();
            switch (b.winner) {
                case 1:
                    wins[0]++;
                    stats.addValue(b.score);
                    break;
                case 2:
                    wins[1]++;
                    stats.addValue(-b.score);
                    break;
                case 3:
                    wins[2]++;
                    stats.addValue(0);
                    break;
            }
        }
        System.out.println(statsToString());

    }

    public Double getPercentile(int percentile) {
        return stats.getPercentile(percentile);
    }

    public long getMeanScore() {
        return Math.round(stats.getMean());
    }

    public Double getWinPercent() {
        return ((wins[0].doubleValue() / iterations.doubleValue()) * 100);
    }

    public Double getTiePercent() {
        return ((wins[2].doubleValue() / iterations.doubleValue()) * 100);
    }

    public Double getLosePercent() {
        return ((wins[1].doubleValue() / iterations.doubleValue()) * 100);
    }

    public String statsToString() {
        return "Win Percent: " +
                getWinPercent() + "\n" +
                "Lose Percent: " +
                getLosePercent() + "\n" +
                "Tie Percent: " +
                getTiePercent() + "\n" +
                "Mean Score: " +
                getMeanScore() + "\n" +
                "Percentiles (1, 5 34, 50, 68, 95, 99):" + "\n" +
                getPercentile(1) + ", " +
                getPercentile(5) + ", " +
                getPercentile(34) + ", " +
                getPercentile(50) + ", " +
                getPercentile(68) + ", " +
                getPercentile(95) + ", " +
                getPercentile(99) + "\n";
//        StringBuilder sb = new StringBuilder();
//        sb.append("Win Percent: ");
//        sb.append(getWinPercent()).append("\n");
//        sb.append("Lose Percent: ");
//        sb.append(getLosePercent()).append("\n");
//        sb.append("Tie Percent: ");
//        sb.append(getTiePercent()).append("\n");
//        sb.append("Mean Score: ");
//        sb.append(getMeanScore()).append("\n");
//
//
//        sb.append("Percentiles (1, 5 34, 50, 68, 95, 99):").append("\n");
//        sb.append(
//                getPercentile(1)).append(", ")
//                .append(getPercentile(5)).append(", ")
//                .append(getPercentile(34)).append(", ")
//                .append(getPercentile(50)).append(", ")
//                .append(getPercentile(68)).append(", ")
//                .append(getPercentile(95)).append(", ")
//                .append(getPercentile(99)).append("\n");
//
//        return sb.toString();
    }

}
