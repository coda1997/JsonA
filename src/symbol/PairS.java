package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class PairS extends Symbol {
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        switch (tokenQueue.peek().getTag()){
            case STRING:
                stack.push(new ValueS());
                stack.push(new Token(Token.Tag.SEMI,":"));
                stack.push(new Token(Token.Tag.STRING,"string"));
                break;
            default:
                throw new MyException(tokenQueue.peek());
        }
    }
}
