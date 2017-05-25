package json;

import symbol.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 60440 on 2017/5/25.
 */
public class Lexer {
    private BufferedReader bufferedReader;
    private static Lexer singleLexer = new Lexer();
    public static Lexer getSingleLexer(){return singleLexer;}
    public Lexer setBufferReader(BufferedReader bufferReader){
        this.bufferedReader = bufferReader;
        return this;
    }
    private Lexer() {
        System.out.println("Lexer has beed create only once");
    }

    public ArrayList<Token> scan() throws IOException {
        String currentLine=bufferedReader.readLine();
        System.out.println(currentLine);


        return null;
    }
    private String quitWhiteSpace(String input){

        System.out.println(input.length());
        return null;
    }


}
