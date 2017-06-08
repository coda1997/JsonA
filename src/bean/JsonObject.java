package bean;

import java.util.HashMap;

/**
 * Created by 60440 on 2017/6/8.
 */
public class JsonObject extends MyJson {
    private HashMap<String,MyJson> jsonHashMap = new HashMap<>();
    public MyJson getJsonByKey(String key){
        return jsonHashMap.get(key);
    }
}
