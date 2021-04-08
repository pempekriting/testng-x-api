package utilities;

import org.json.JSONException;
import org.json.JSONObject;

public class Helper {
	public static JSONObject mergeJSONObjects(JSONObject json1, JSONObject json2) {
		JSONObject mergedJSON = new JSONObject();
		try {
			mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
			for (String jsonAttribute: JSONObject.getNames(json2)) {
				mergedJSON.put(jsonAttribute, json2.get(jsonAttribute));
			}

		} catch (JSONException e) {
			throw new RuntimeException("JSON Exception" + e);
		}
		return mergedJSON;
	}
}
