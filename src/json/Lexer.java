package json;

import myException.MyException;
import symbol.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 60440 on 2017/5/25.
 */
public class Lexer {
    private BufferedReader bufferedReader;
    private static int line=1;
    private static int position =1;
    private char currentChar = ' ';
    private ArrayList<Token> tokens = new ArrayList<>();
    private boolean isEndOfFile = false;
    private char beforeChar=' ';


    private static Lexer singleLexer = new Lexer();


    public static Lexer getSingleLexer(){return singleLexer;}
    public Lexer setBufferReader(BufferedReader bufferReader){
        this.bufferedReader = bufferReader;
        return this;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public boolean isEnd(){return isEndOfFile;}

    private Lexer() {

    }

    public void scan() throws MyException {
        quitWitespace();
        if(getNumToken()||getIdToken()||getOpToken()||getSpecValue());
    }

    private boolean readNext() throws MyException {
        try {
            beforeChar=currentChar;
            int temp=bufferedReader.read();
            if(temp==-1){
                isEndOfFile=true;
                return false;
            }
            currentChar=(char) temp;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new MyException("line "+line+",position "+position+": causeBy<"+beforeChar+">");

        }finally {
            position++;
        }
        return true;
    }

    private boolean isOperatior(char c){
        if(c==':'||c==','||c=='['||c==']'||c=='{' ||c=='}'){
            return true;
        }
        return false;
    }

    private void quitWitespace()throws MyException{
        try {
            for( ; ; readNext()) {
                if(isEndOfFile){
                    return;
                }
                if(currentChar == ' ')
                    continue;
                else if(currentChar=='\n'){
                    position--;
                    continue;
                }else if (currentChar=='\t'){
                  position+=7;
                  continue;
                } else if (currentChar=='\r'){
                    position=0;
                    line++;
                }else
                    break;
            }
        } catch (MyException e) {
            throw e;
        }
    }

    private boolean getOpToken() throws MyException{
        if(isOperatior(currentChar)){
            Token token = null;
            switch (currentChar){
                case '{':
                    token=new Token(Token.Tag.LB,"{");
                    break;
                case '}':
                    token=new Token(Token.Tag.RB,"}");
                    break;
                case '[':
                    token=new Token(Token.Tag.LBK,"[");
                    break;
                case ']':
                    token=new Token(Token.Tag.RBK,"]");
                    break;
                case ':':
                    token=new Token(Token.Tag.SEMI,":");
                    break;
                case ',':
                    token = new Token(Token.Tag.COMMA,",");
                    break;
                default:
                    throw new MyException("line "+line+",position "+position+": causeBy<"+beforeChar+">");
            }
            token.setLine(line);
            token.setPosition(position);
            tokens.add(token);
            readNext();
            return true;
        }else {
            return false;
        }
    }

    private boolean getIdToken() throws MyException{
        // TODO Auto-generated method stub
        if((!isEnd())&&currentChar=='"'){
            StringBuffer word = new StringBuffer();
            word.append(currentChar);
            readNext();
            while (currentChar!='"'){
                if (currentChar=='\\'){
                    word.append(currentChar);
                    readNext();
                    word.append(currentChar);
                    readNext();
                }else {
                    word.append(currentChar);
                    readNext();
                }
            }
            word.append(currentChar);
            String wordString = word.toString();
            Token token=new Token(Token.Tag.STRING,wordString);
            token.setLine(line);
            token.setPosition(position);
            //avoid put wrong position info into the token

            readNext();

            tokens.add(token);
            return true;
        }else {
            return false;
        }
    }

    private boolean getSpecValue() throws MyException{
        boolean isError = true;
        if(currentChar=='f'){
            readNext();
            if(currentChar=='a'){
                readNext();
                if (currentChar=='l'){
                    readNext();
                    if(currentChar=='s'){
                        readNext();
                        if(currentChar=='e'){
                            Token token = new Token(Token.Tag.FALSET,"false");
                            token.setLine(line);
                            token.setPosition(position);
                            tokens.add(token);
                            readNext();
                            isError=false;
                            return true;
                        }
                    }
                }
            }
        }

        if(currentChar=='n'){
            readNext();
            if(currentChar=='u'){
                readNext();
                if(currentChar=='l'){
                    readNext();
                    if(currentChar=='l'){
                        Token token = new Token(Token.Tag.NULLT,"null");
                        token.setLine(line);
                        token.setPosition(position);
                        tokens.add(token);
                        readNext();
                        isError=false;
                        return true;
                    }
                }
            }

        }
        if(currentChar=='t'){
            readNext();
            if(currentChar=='r'){
                readNext();
                if (currentChar=='u'){
                    readNext();
                    if (currentChar=='e'){
                        Token token = new Token(Token.Tag.TRUET,"true");
                        token.setLine(line);
                        token.setPosition(position);
                        tokens.add(token);
                        readNext();
                        isError=false;
                        return true;
                    }
                }
            }
        }


        if(isError==true){
            throw new MyException("line "+line+",position "+position+": causeBy<"+beforeChar+">");
        }
        return false;
    }


    private boolean getNumToken() throws MyException{
        StringBuffer stringBuffer = new StringBuffer();

        if(currentChar=='-'){
            stringBuffer.append('-');
            readNext();
        }
        if(currentChar=='0'){
            stringBuffer.append(currentChar);
            readNext();
            if(currentChar=='.'){
                stringBuffer.append(currentChar);
                readNext();
                while (Character.isDigit(currentChar)){
                    stringBuffer.append(currentChar);
                    readNext();
                }
                if(getScien(stringBuffer)){
                    Token token = new Token(Token.Tag.SCIENTIFIC,stringBuffer.toString());
                    token.setPosition(position);
                    token.setLine(line);
                    tokens.add(token);
                }else {
                    Token token = new Token(Token.Tag.FLOAT,stringBuffer.toString());
                    token.setPosition(position);
                    token.setLine(line);
                    tokens.add(token);
                }
            }else {
                Token token = new Token(Token.Tag.INTEGER,stringBuffer.toString());
                token.setPosition(position);
                token.setLine(line);
                tokens.add(token);
            }
            return true;
        }
        if(currentChar=='1'||currentChar=='2'||currentChar=='3'||currentChar=='4'||currentChar=='5'||currentChar=='6'
                ||currentChar=='7'||currentChar=='9'||currentChar=='9'){
            do{
                stringBuffer.append(currentChar);
                readNext();
            }while (Character.isDigit(currentChar));
            if(currentChar=='.'){
                stringBuffer.append(currentChar);
                readNext();
                while (Character.isDigit(currentChar)){
                    stringBuffer.append(currentChar);
                    readNext();
                }
                if(getScien(stringBuffer)){
                    Token token = new Token(Token.Tag.SCIENTIFIC,stringBuffer.toString());
                    token.setPosition(position);
                    token.setLine(line);
                    tokens.add(token);
                }else {
                    Token token = new Token(Token.Tag.FLOAT,stringBuffer.toString());
                    token.setPosition(position);
                    token.setLine(line);
                    tokens.add(token);
                }
            }else {
                Token token = new Token(Token.Tag.INTEGER,stringBuffer.toString());
                token.setPosition(position);
                token.setLine(line);
                tokens.add(token);
            }
            return true;
        }
        return false;
    }
    private boolean getScien(StringBuffer stringBuffer) throws MyException{
        if(currentChar=='e'||currentChar=='E'){
            stringBuffer.append(currentChar);
            readNext();
            if(currentChar=='+'||currentChar=='-'){
                stringBuffer.append(currentChar);
                readNext();
            }
            if(Character.isDigit(currentChar)){
                do{
                    stringBuffer.append(currentChar);
                    readNext();
                }while (Character.isDigit(currentChar));
                return true;
            }else {
                throw new MyException("line "+line+",position "+position+": causeBy<"+beforeChar+">");
            }
        }else{
            return false;

        }

    }

}
