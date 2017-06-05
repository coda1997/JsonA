package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class MemberYS extends Symbol {
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        switch (tokenQueue.peek().getTag()){
            case RB:
                break;
            case COMMA:
                stack.push(new MemberXS());
                stack.push(new Token(Token.Tag.COMMA,","));
                break;
            default:
                throw new MyException(tokenQueue.peek());
        }
    }
}
