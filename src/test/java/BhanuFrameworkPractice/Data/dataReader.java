package BhanuFrameworkPractice.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		//reading the json to string
		String json=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src\\test\\java\\BhanuFrameworkPractice\\Data\\purchaseOrder.json"),
				StandardCharsets.UTF_8);
	//convert json to HashMap Jackson DATA Bind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(json, new TypeReference<List<HashMap<String,String>>>(){});
		
	return data;

}
}
