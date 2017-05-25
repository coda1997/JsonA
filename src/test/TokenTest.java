package test;

import symbol.Token;

/**
 * Created by 60440 on 2017/5/24.
 */
public class TokenTest {
    public static void main(String[] args) {
        System.out.println("Token testing");

        Token token = new Token(Token.Tag.INTEGER,"hello");
        Token token1 = new Token(Token.Tag.INTEGER,"hello");
        System.out.println(token.tokenEqual(token1));
        System.out.println(token.valueEqual(token1));


    }



}
