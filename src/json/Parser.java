package json;

import myException.MyException;
import symbol.Token;

import java.util.ArrayList;
/**
 * Created by 60440 on 2017/6/5.
 */

public class Parser {
    private ArrayList<Token> tokens;
    private int index=0;
    public Parser(ArrayList<Token> tokens){

        this.tokens = tokens;
    }

    private Token getNext(){
        if(index<tokens.size()) {
            return tokens.get(index);
        }else {
            return null;
        }
    }
    private void doNext(){
        if(index<tokens.size()){
            index++;
        }
    }

    public void parse() throws MyException {
        if(json()){
            System.out.println("Valid Json");
        }else {
            if (getNext()==null){
                throw new MyException(tokens.get(tokens.size()-1));
            }else {

                throw new MyException(getNext());
            }
        }

    }

    private boolean json(){
        int i =index;
        boolean res= object()||array();
        if(res){
            return true;
        }else {
            index=i;
            return false;
        }
    }

    private boolean array() {
        int i = index;
        boolean res = checkToken(Token.Tag.LBK)&&elements()&&checkToken(Token.Tag.RBK)
                ||checkToken(Token.Tag.LBK)&&checkToken(Token.Tag.RBK);
        if (res){
            return true;
        }else {
            index = i;
            return false;
        }

    }

    private boolean elements() {
        int i = index;
        boolean res = value()&&checkToken(Token.Tag.COMMA)&&elements()||value();
        if (res){
            return true;
        }else {
            index = i;
            return false;
        }
    }

    private boolean object() {
        int i =index;
        boolean res = checkToken(Token.Tag.LB)&&checkToken(Token.Tag.RB)
                ||checkToken(Token.Tag.LB)&&members()&&checkToken(Token.Tag.RB);
        if (res){
            return true;
        }else {
            index=i;
            return false;
        }
    }

    private boolean members() {
        int i = index;
        boolean res = pair()&&checkToken(Token.Tag.COMMA)&&members()||pair();
        if(res){
            return true;
        }else {
            index = i;
            return false;
        }
    }

    private boolean pair() {
        int i =index;
        boolean res = checkToken(Token.Tag.STRING)&&checkToken(Token.Tag.SEMI)&&value();
        if (res){
            return true;
        }else {
            index = i;
            return false;
        }
    }

    private boolean value() {
        int i = index;
        boolean res = checkToken(Token.Tag.STRING)||number()||object()||array()||checkToken(Token.Tag.TRUET)
                ||checkToken(Token.Tag.FALSET)||checkToken(Token.Tag.NULLT);
        if (res){
            return true;

        }else {
            index = i;
            return false;
        }
    }

    private boolean number() {
        int i = index;
        boolean res = checkToken(Token.Tag.INTEGER)||checkToken(Token.Tag.FLOAT)||checkToken(Token.Tag.SCIENTIFIC);
        if (res){
            return true;
        }else {
            index=i;
            return false;
        }
    }

    private boolean checkToken(Token.Tag tag){
        Token token = getNext();

        if(token!=null&&token.getTag().equals(tag)){
            doNext();
            return true;
        }else {
            return false;
        }
    }


}
