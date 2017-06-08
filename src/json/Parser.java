package json;

import myException.MyException;
import symbol.DollarS;
import symbol.JsonS;
import symbol.Symbol;
import symbol.Token;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */

public class Parser {
    private ArrayList<Token> tokens;
    private int index=0;
    public Parser(ArrayList<Token> tokens){

        this.tokens = tokens;
    }



    public void parse()  {
        Queue<Token> tokenQueue = new LinkedList<>();
        for (Token token:tokens
             ) {
            tokenQueue.add(token);
        }
        tokenQueue.add(new Token(Token.Tag.DOLLAR,"dollar"));
        Stack<Symbol> stack= new Stack<>();
        stack.push(new DollarS());
        stack.push(new JsonS());
        try {
            while (!stack.isEmpty()){
                stack.pop().detect(stack,tokenQueue);
            }

        } catch (MyException e) {
            System.out.println(e.getMessage());
        }


    }




}
