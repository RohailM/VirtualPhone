package Weather;

public class Model {
	
	private Weather weather;
	private String currentTemp;
	private String longDescription;
	public boolean valid;
	
	public Model(Weather w) {
		this.weather = w;
	}
	
	public String getCurrentTemp() {
		return this.currentTemp;
	}
	
	public String getLongDescription() {
		return this.longDescription;
	}
	
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

