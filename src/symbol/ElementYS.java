package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class ElementYS extends Symbol {
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        switch (tokenQueue.peek().getTag()){
            case COMMA:
                stack.push(new ElementXS());
                stack.push(new Token(Token.Tag.COMMA,","));
                break;
            case RBK:
                break;
            default:
                throw new MyException(tokenQueue.peek());
        }
    }
}
