package Test;

import Symbol.Token;

/**
 * Created by 60440 on 2017/5/24.
 */
public class TokenTest {
    public static void main(String[] args) {
        System.out.println("Token testing");

        Token token = new Token(Token.Tag.INTEGER);
        Token token1 = new Token(Token.Tag.INTEGER);
        System.out.println(token.tokenEqual(token1));


    }



}
