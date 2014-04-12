import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class weather {
	static class Answer {
		Conditions current_observation;
	}
	
	static class Conditions {
		String weather;
		double temp_f;
	}
	
	public static String getWeather(double lon, double lat) throws Exception{
		Gson gson = new Gson();
		String part = lon + "," + lat;
		
		String info = readUrl("http://api.wunderground.com/api/45b815d2d393192c/" + "conditions/q/" + part + ".json");
		Answer answer = (Answer) gson.fromJson(info, Answer.class);
		
		String weather = answer.current_observation.weather.toLowerCase();
		
		if (weather.contains("cloud") || weather.contains("overcast") || weather.contains("fog")) {
			weather = "cloud";
		} else if (weather.contains("rain") || weather.contains("mist") || weather.contains("spray") || weather.contains("precipitation")) {
			weather = "rain";
		} else if (weather.contains("snow") || weather.contains("ice")) {
			weather = "snow";
		} else if (weather.contains("thunderstorm")) {
			weather = "thunderstorm";
		} else if (weather.contains("clear")) {
			weather = "clear";
		} else {
			weather = "cloud";
		}
		
		return weather;
		
	}
	
	public static double getTemp(double lon, double lat) throws Exception{
		Gson gson = new Gson();
		String part = lon + "," + lat;
		
		String info = readUrl("http://api.wunderground.com/api/45b815d2d393192c/" + "conditions/q/" + part + ".json");
		Answer answer = (Answer) gson.fromJson(info, Answer.class);
		
		return answer.current_observation.temp_f;
	}
	
	public static String readUrl(String url) throws Exception {
		URL site = new URL(url);
		URLConnection connection = site.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		StringBuilder info = new StringBuilder();
		String inputLine;
		
		inputLine = reader.readLine();
		while (inputLine != null) {
			info.append(inputLine);
			inputLine = reader.readLine();
		}
		
		reader.close();
		
		return info.toString();
	}
	
	public static void main(String[] args) throws Exception {
		
	}
}
