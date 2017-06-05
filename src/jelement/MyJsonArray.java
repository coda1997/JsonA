package jelement;

import java.util.ArrayList;

/**
 * Created by 60440 on 2017/6/5.
 */
public class MyJsonArray extends MyJson {
    private ArrayList<MyJson> myJsons = new ArrayList<>();

    public MyJsonArray(ArrayList<MyJson> list){
        myJsons=list;
    }

    public int length(){return myJsons.size();}

    public void add(MyJson element){
        myJsons.add(element);

    }

    public MyJson get(int i ){
        return myJsons.get(i);

    }

    @Override
    public String toString() {
        return "MyJsonArray{" +
                "myJsons=" + myJsons +
                '}';
    }
}
