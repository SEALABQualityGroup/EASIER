package it.univaq.disim.sealab.metaheuristic.evolutionary.operator;

import org.uma.jmetal.operator.crossover.CrossoverOperator;

//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU Lesser General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU Lesser General Public License for more details.
//
//You should have received a copy of the GNU Lesser General Public License
//along with this program.  If not, see <http://www.gnu.org/licenses/>.

import org.uma.jmetal.util.JMetalException;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;

public abstract class RCrossover<S extends RSolution<?>> implements CrossoverOperator<S> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected double crossoverProbability;

	/** Constructor */
	public RCrossover(double crossoverProbability) {
		if (crossoverProbability < 0) {
			throw new JMetalException("Crossover probability is negative: " + crossoverProbability);
		}
		this.crossoverProbability = crossoverProbability;
//		randomGenerator = JMetalRandom.getInstance();
//		controller = ctrl;
	}

	/* Getter */
	public double getCrossoverProbability() {
		return crossoverProbability;
	}


	@Override
	public int getNumberOfRequiredParents() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getNumberOfGeneratedChildren() {
		// TODO Auto-generated method stub
		return 4;
	}

}
