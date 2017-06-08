package json;

import myException.MyException;
import symbol.Token;
import utils.FormatUtils;
import utils.JsonUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by 60440 on 2017/6/5.
 */
public class JsonMain {
    public static void main(String[] args) {
        String filePath = "";
        boolean isNeed = false;
        boolean isFind = false;
        int start=0;
        if(args[0].equals("-pretty")){
            isNeed=true;
            start=1;
        }else if(args[0].equals("-find")){
            isFind=true;
            start=2;
        }

        for(;start<args.length;start++){
            filePath=args[start];
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                Lexer lexer = Lexer.getSingleLexer().setBufferReader(bufferedReader);
                while (!lexer.isEnd()){

                    lexer.scan();
                }
                ArrayList<Token> tokens=lexer.getTokens();

                if(tokens.size()>0){

                    Parser parser = new Parser(tokens);
                    parser.parse();
                    System.out.println("Valid");
                }
                if(isNeed){
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < tokens.size(); i++) {
                        stringBuffer.append(tokens.get(i).getTokenValue());
                    }
                    String res = FormatUtils.formatJson(stringBuffer.toString());
                    String fileP = filePath.substring(0,filePath.length()-5)+"pretty.json";
                    FormatUtils.toPrettyFile(res,fileP);
                }

                if (isFind){
                    System.out.println(JsonUtils.jsonFind(tokens,args[1]));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
