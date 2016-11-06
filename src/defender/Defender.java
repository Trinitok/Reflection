package defender;

public class Defender {
	
	//  private fields
	public static final int numSoldiers = java.lang.Integer.MAX_VALUE;  //  not sure how many attackers there are
	private String soldiers = "Defend this class!";  //  they will be saying things to eachother
	static king gilgy;
	
	//  because we can hear them yell at us
	public static final String warCry = "Death to the Attacker!";
	
	//  private constructors
	private Defender(){
		
	}
	
	
	//  private methods
	private int getNumSoldiers(){
		return numSoldiers;
	}
	
	
	//  private inner (hidden?) class
	private static class king{
		private static String name = "Gilgamesh";
		private static final boolean alive = true;
		private king(){
			
		}
		public Boolean getKingStatus(){
			return alive;
		}
		private String listenToKing(){
			return "I reign supreme!";
		}
	}
}
