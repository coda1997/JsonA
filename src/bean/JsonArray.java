package bean;

import java.util.ArrayList;

/**
 * Created by 60440 on 2017/6/8.
 */
public class JsonArray extends MyJson{
    private ArrayList<MyJson> jsonElements=new ArrayList<>();
    public MyJson getJsonByIndex(int i){
        if(i>=0&&i<jsonElements.size()){
            return jsonElements.get(i);
        }else {
            return null;
        }
    }
}
