package TestData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonData() throws IOException {
		
		//Read json to string -- Java property
		File file = new File(System.getProperty("user.dir")+"//src//test//java//TestData//RoomReservationData.json");
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		//converting string to Hashmap -- Jackson DataBind dependency needs to be added to the pom.xml
		
		ObjectMapper map = new ObjectMapper();
		List<HashMap<String, String>> data = map.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
	});
		return data;

	}
}
