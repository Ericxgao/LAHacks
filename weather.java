import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class weather {
	static class Answer {
		Response response;
		Conditions current_observation;
	}
	
	static class Response {
		String version;
	}
	
	static class Conditions {
		String weather;
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
		Gson gson = new Gson();
		String info = readUrl("http://api.wunderground.com/api/45b815d2d393192c/" + "conditions/q/37.8,-122.4.json");
		
		Answer answer = (Answer) gson.fromJson(info, Answer.class);
		
		System.out.println( answer.response.version );
		System.out.println( answer.current_observation.weather );
		
	}
}
