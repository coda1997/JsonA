package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public interface DetectInterface {
    void detect (Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException;
}
