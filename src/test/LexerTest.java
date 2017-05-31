package test;

import json.Lexer;
import myException.MyException;
import symbol.Token;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 60440 on 2017/5/25.
 */
public class LexerTest {
    private final static String filePath = "src/test01.txt";

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            Lexer lexer = Lexer.getSingleLexer().setBufferReader(bufferedReader);
            while (!lexer.isEnd()){

                lexer.scan();
            }
            ArrayList<Token> tokens=lexer.getTokens();
            for (Token token:tokens
                 ) {
                System.out.println(token);
            }
            bufferedReader.close();
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
