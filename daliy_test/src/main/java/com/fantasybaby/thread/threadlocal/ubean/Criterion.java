package com.fantasybaby.thread.threadlocal.ubean;
/**
 * use to send search condition by threadlocal
 * @author FantasyBaby
 *
 * @param <T>
 */
public class Criterion<T> {
	private T criterion;

	public T getCriterion() {
		return criterion;
	}

	public void setCriterion(T criterion) {
		this.criterion = criterion;
	}
}
