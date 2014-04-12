import java.util.Arrays;

public class Pokemon {
	private final double GOODMULTIPLIER = 1.25;
	private final double BADMULTIPLIER = 0.75;
	private double chance;
	private String name;
	private int number;
	private String[] goodConditions;
	private String[] badConditions;
	private int tempRange;
	
	public Pokemon (double chance, String name, int number, String[] goodConditions, String[] badConditions, int tempRange) {
		// Base chance of catching this pokemon, adjust based on chance
		this.chance = chance;
		// Name of the pokemon
		this.name = name;
		// Number of the pokemon (in the pokedex)
		this.number = number;
		// List of conditions in which this pokemon is more prominent (25% more)
		this.goodConditions = goodConditions;
		// List of conditions in which this pokemon is less prominent (25% less)
		this.badConditions = badConditions;
		// The pokemon exists more at this temperature +- 10. Set to 200 if Pokemon exists in all ranges
		this.tempRange = tempRange;
	}
	
	private double tempChance (double temp) {
		if (temp <= this.tempRange + 3 || temp >= this.tempRange - 3) {
			return 1.00;
		} else {
			double newChance = 1.00 - (Math.abs(temp - tempRange) - 10) * 0.1;
			if (newChance < 0) {
				return 0;
			} else {
				return newChance;
			}
		}
	}
	
	public double getChance(double lon, double lat) throws Exception{
		String conditions = weather.getWeather(lon, lat);
		double temp = weather.getTemp(lon,  lat);
		
		double prob = this.chance;
		
		if (!Arrays.asList(this.goodConditions).contains("all")) {
			if (Arrays.asList(this.goodConditions).contains(conditions)) {
				prob *= GOODMULTIPLIER;
			}
			
			if (Arrays.asList(this.badConditions).contains(conditions)) {
				prob *= BADMULTIPLIER;
			}
		}
		
		prob *= tempChance(temp);
		
		return prob;
	}
}