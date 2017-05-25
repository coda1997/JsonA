package test;

import json.Lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 60440 on 2017/5/25.
 */
public class LexerTest {
    private final static String filePath = "src/test01.txt";

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            Lexer lexer = Lexer.getSingleLexer().setBufferReader(bufferedReader);
            lexer.scan();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
