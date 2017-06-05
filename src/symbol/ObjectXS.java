package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class ObjectXS extends Symbol {
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        switch (tokenQueue.peek().getTag()){
            case LB:
                stack.push(new ObjectYS());
                stack.push(new Token(Token.Tag.LB,"{"));
                break;
            default:
                throw new MyException(tokenQueue.peek());
        }
    }
}