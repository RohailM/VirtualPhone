package Weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

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
	
	public String getCity () { 
		return this.city; 
	}
	
	public String getLongDescription() {
		return this.longDescription;
	}
	
	public String getBriefDescription () {
		return this.briefDescription;
	}
	
	public String getCurrentTemp() {
		return this.temp; 
	}
	
	public String getMaxTemp() {
		return this.maxTemp;
	}
	
	public String getMinTemp() {
		return this.minTemp;
	}
	
	public void init(String c) {
		this.city = c;
		try {
			sendGet();
		} catch (Exception e) {
			System.out.println("ADAM: GET FAILED");
			e.printStackTrace();
		}
	}
	
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
