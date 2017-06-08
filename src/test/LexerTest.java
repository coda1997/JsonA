package test;

import json.Lexer;
import json.Parser;
import myException.MyException;
import symbol.Token;
import utils.JsonUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 60440 on 2017/5/25.
 */
public class LexerTest {
    private final static String filePath = "src/jsontest/f/country.json";
    private final static String filePathf = "src/jsontest/e/false.json";


    public static void main(String[] args) {
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

            System.out.println(JsonUtils.jsonFind(tokens,"/RECORDS[35]/countryname"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
        }
    }
}
