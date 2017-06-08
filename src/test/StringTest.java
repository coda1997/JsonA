package test;

import java.util.StringTokenizer;

/**
 * Created by 60440 on 2017/6/8.
 */
public class StringTest {
    public static void main(String[] args) {
        String path = "/RECORDS[35]/countryname";
        StringTokenizer stringTokenizer = new StringTokenizer(path,"/");

        while (stringTokenizer.hasMoreTokens()){
            String path1 = stringTokenizer.nextToken();
            System.out.println(path1);
            if(path1.indexOf('[')!=-1){
                String index = path1.substring(path1.indexOf('[')+1,path1.length()-1);
                System.out.println(index);
            }
        }
    }
}
