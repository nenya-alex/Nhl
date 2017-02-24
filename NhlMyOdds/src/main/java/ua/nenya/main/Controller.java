package ua.nenya.main;

import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;

public class Controller {
	
	private Calculator calculator = new Calculator();
	
	public void setNewOddsInGames(List<Game> games, List<Dot> dots) {		
		for (int i = 200; i < games.size(); i++) {
			calculator.calculateNewOdds(games.get(i), dots);					
		}
	}
}
