package utils;

import others.Location;

public class PairOfLocationsTuple {
	Location loc1;
	Location loc2;
	
	public PairOfLocationsTuple(Location loc1, Location loc2) {
		super();
		this.loc1 = loc1;
		this.loc2 = loc2;
	}

	public Location getLoc1() {
		return loc1;
	}
	
	public void setLoc1(Location ind1) {
		this.loc1 = ind1;
	}
	
	public Location getLoc2() {
		return loc2;
	}
	
	public void setLoc2(Location ind2) {
		this.loc2 = ind2;
	}
	
	public boolean equals(PairOfLocationsTuple tuple2) {
		return	this.getLoc1().equals(tuple2.getLoc1()) && this.getLoc2().equals(tuple2.getLoc2()) ||
				this.getLoc1().equals(tuple2.getLoc2()) && this.getLoc2().equals(tuple2.getLoc1());
	}
}