package Weather;
//100% Rohail
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

// This class is a JSON reader that fetches the weather from the OpenWeatherMap API. Each object of this class requires a city as an argument to create.
public class Weather {
	
	private String temp;
	private String minTemp;
	private String maxTemp;
	
	private String briefDescription;
	private String longDescription;
	private String city;
	public boolean validRequest;
	
	public Weather (String l) {
		this.city = l;
	}
	
	/*
	 * This method returns the instance variable city.
	 * 
	 * @param	None
	 * @return	String		the city
	 */
	public String getCity () { 
		return this.city; 
	}
	
	/*
	 * This method returns the instance variable longDescription.
	 * 
	 * @param	None
	 * @return	String		long description of the city
	 */
	public String getLongDescription() {
		return this.longDescription;
	}
	
	/*
	 * This method returns the instance variable briefDescription.
	 * 
	 * @param	None
	 * @return	String		brief description of the city
	 */
	public String getBriefDescription () {
		return this.briefDescription;
	}
	
	/*
	 * This method returns the instance variable temp.
	 * 
	 * @param	None
	 * @return	String		current temperature
	 */
	public String getCurrentTemp() {
		return this.temp; 
	}
	
	/*
	 * This method returns the instance variable maxTemp.
	 * 
	 * @param	None
	 * @return	String		the maximum temperature
	 */
	public String getMaxTemp() {
		return this.maxTemp;
	}
	
	/*
	 * This method returns the instance variable minTemp.
	 * 
	 * @param	None
	 * @return	String		the minimum temperature
	 */
	public String getMinTemp() {
		return this.minTemp;
	}
	
	/*
	 * This is the first method that is called on an object of the Weather class, and it calls another method that reads the information from OpenWeatherMap API.
	 * 
	 * @param	c	the city that we will be checking the weather of
	 * @return	void
	 */
	public void init(String c) {
		this.city = c;
		try {
			sendGet();
		} catch (Exception e) {
			System.out.println("Fetch FAILED");
			e.printStackTrace();
		}
	}
	
	/*
	 * This is the method that reads OpenWeatherMap API to get the weather of the selected city, and updates all instance variables accordingly. It may throw an Exception.
	 */
	private void sendGet() throws Exception {

		String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=c638df7df005825d4d76a8bf0115679d";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		if (responseCode == 200) {
			this.validRequest = true;
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		
			
			//print result
			System.out.println(response.toString());
			JSONParser jsonParser = new JSONParser();
			try {

				Object object = jsonParser.parse(response.toString());

				JSONObject jsonObject = (JSONObject) object;
				
				JSONArray weather = (JSONArray)jsonObject.get("weather");
				
				JSONObject weatherDetail =  (JSONObject)weather.get(0);
				
				String weatherDescription = weatherDetail.get("description").toString();

				JSONObject temperatureInfo = (JSONObject)jsonObject.get("main");
		
				Double currentTemp = (double) Math.round(Double.parseDouble(temperatureInfo.get("temp").toString()) - 273);
				String currentTemperature = currentTemp.toString();
				
				Double minimumTemp = (double) Math.round(Double.parseDouble(temperatureInfo.get("temp_min").toString()) - 273);
				String minTemp = minimumTemp.toString();
				
				Double maximumTemp = (double) Math.round(Double.parseDouble(temperatureInfo.get("temp_max").toString()) - 273);
				String maxTemp = maximumTemp.toString();
				
				String mainWeather = weatherDetail.get("main").toString();
				this.temp = currentTemperature;
				this.briefDescription = mainWeather;
				this.longDescription = weatherDescription;
				this.minTemp = minTemp;
				this.maxTemp = maxTemp;
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else {
			this.validRequest = false;
		}
	}
	
}
