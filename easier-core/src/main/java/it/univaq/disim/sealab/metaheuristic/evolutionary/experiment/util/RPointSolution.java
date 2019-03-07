package it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.util;

import java.util.List;

import org.uma.jmetal.util.point.util.PointSolution;

public class RPointSolution extends PointSolution{
	
	private int ID;
	
	public RPointSolution(int numberOfObjectives) {super(numberOfObjectives);}
	
	public RPointSolution() {super(3);}
	
	public RPointSolution setID(int id) {
		this.ID = id;
		return this;
	}
	
	public RPointSolution setPointSolution(final List<String> obj) {
		
		for(int i = 0; i < obj.size(); i++) {
			super.setObjective(i, Double.parseDouble(obj.get(i)));
		}
		
		return this;
	}
	
	public int getID() { return ID;}
	
}
