package com.alanbaumgartner.bgsim;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulation {

	private final int MAX_THREADS = 8;
	private ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);
	private Board[] simulations;

	private Player one;
	private Player two;

	private Integer[] wins;

	private Integer iterations;

	private DescriptiveStatistics stats = new DescriptiveStatistics();

	public Simulation(Player one, Player two, int iterations) {
		this.one = one;
		this.two = two;
		this.iterations = iterations;
		this.simulations = new Board[iterations];
		for (int i = 0; i < iterations; i++) {
			this.simulations[i] = new Board(new Player(one), new Player(two));
		}
		wins = new Integer[]{0, 0, 0};
	}

	public void simulate() {
		for (Board b : simulations) {
			threadPool.execute(b);
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

		} catch (InterruptedException ex) {

		}
		for (Board b : simulations) {
			switch (b.winner) {
				case 1:
					wins[0]++;
					stats.addValue(b.score);
					break;
				case 2:
					wins[1]++;
					stats.addValue(-b.score);
					break;
				case 0:
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
	}

}
