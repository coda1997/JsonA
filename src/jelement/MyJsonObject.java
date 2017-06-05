package jelement;

import symbol.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 60440 on 2017/6/5.
 */
public class MyJsonObject extends MyJson {
    private Map<String,Token> map = new HashMap<>();

    public MyJsonObject(Map<String,Token> map){
        this.map=map;
    }

    public String getValue(String key){
        return map.get(key).getTokenValue();
    }

    public String getValueType(String key){
        return map.get(key).getTag().toString().toLowerCase();
    }


}
