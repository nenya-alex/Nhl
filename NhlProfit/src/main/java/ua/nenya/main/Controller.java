package ua.nenya.main;

import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.domain.Game;
import ua.nenya.domain.Profit;

public class Controller {
	
	private Calculator calculator = new Calculator();
	
	public Profit getProfitByCaseId(List<Game> games, int caseId, double bet, double initialSum) {
		Profit profit = new Profit();
		profit.setTotalSum(initialSum);
		for (int i = 200; i < games.size(); i++) {
			calculator.calculateProfitByCaseId(games.get(i), caseId, bet, profit);					
		}
		return profit;		
	}
}
