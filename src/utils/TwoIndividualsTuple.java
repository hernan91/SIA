package utils;

import individuals.Individual;

public class TwoIndividualsTuple {
	Individual ind1;
	Individual ind2;
	
	public TwoIndividualsTuple(Individual ind1, Individual ind2) {
		super();
		this.ind1 = ind1;
		this.ind2 = ind2;
	}
	
	public Individual getInd1() {
		return ind1;
	}
	
	public void setInd1(Individual ind1) {
		this.ind1 = ind1;
	}
	
	public Individual getInd2() {
		return ind2;
	}
	
	public void setInd2(Individual ind2) {
		this.ind2 = ind2;
	}
}
