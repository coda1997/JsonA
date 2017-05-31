package myException;

import symbol.Token;

/**
 * Created by 60440 on 2017/5/25.
 */
public class MyException extends Exception {
    private String message = "null";
    public String getMessage() {

        return message;
    }
    public MyException(String msg){
        message=msg;
    }
    public MyException(Token token){
        message=token.getErrMss();
    }
}
