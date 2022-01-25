package Weather;
// 100% Rohail
// A class used to model a city, which holds all the information necessary for the program relating to the city the user selects.
public class City {
	
	private Weather weather;
	private String currentTemp;
	private String longDescription;
	public boolean valid;
	
	public City(Weather w) {
		this.weather = w;
	}
	
	/*
	 * This method simply returns the instance variable currentTemp.
	 * 
	 * @param	None
	 * @return	String	a string containing the temperature (the instance variable currentTemp)
	 */
	public String getCurrentTemp() {
		return this.currentTemp;
	}
	
	/*
	 * This method simply returns the instance variable longDescription.
	 * 
	 * @param	None
	 * @return	String	a string containing a long description on the city's climate
	 */
	public String getLongDescription() {
		return this.longDescription;
	}

	/*
	 * This method creates an instance of the weather class and fetches the necessary information and assigns the values to the instance variables.
	 * 
	 * @param	s	the city who's weather needs to be fetched
	 * @return	void	doesn't need to return a value, all this method does is assign values to instance variables
	 */
	public void init(String s) {
		weather.init(s);
		if(weather.validRequest) {
			this.currentTemp = weather.getCurrentTemp();
			this.longDescription = weather.getLongDescription();
			this.valid = true;
		}else {
			this.valid = false;
		}
		
		
	}
}

