package myException;

import symbol.Token;

/**
 * Created by 60440 on 2017/5/25.
 */
public class MyException extends Exception {
    public String getMessage(Token token) {

        return token.getErrMss();
    }
}
