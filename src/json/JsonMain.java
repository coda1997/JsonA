package json;

import myException.MyException;
import symbol.Token;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by 60440 on 2017/6/5.
 */
public class JsonMain {
    public static void main(String[] args) {
        String filePath = "src/test01.txt";
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
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
