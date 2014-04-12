
public class pokeSelect {
	public static void main (String[] args) throws Exception{
		Pokemon bulbasaur = new Pokemon(0.5, "Bulbasaur", 1, new String[] { "clear" }, new String[] { "rain" }, 60);
		System.out.println(bulbasaur.getChance(34.1, -118.3));
	}
}
