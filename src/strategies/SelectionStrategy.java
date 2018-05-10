package strategies;

import generics.ObjectiveFunction;
import generics.Population;
import operators.BinaryTournamentSelector;

public class SelectionStrategy {
	public Population selectWithBinaryTournament(Population population, ObjectiveFunction objFunct) {
		BinaryTournamentSelector btSelector = new BinaryTournamentSelector(objFunct);
		return btSelector.selectIndividuals(population);
	}
}
