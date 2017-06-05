package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class NumberS extends Symbol{
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        switch (tokenQueue.peek().getTag()){
            case INTEGER:
                stack.push(new Token(Token.Tag.INTEGER,"int"));break;
            case FLOAT:
                stack.push(new Token(Token.Tag.FLOAT,"float"));break;
            case SCIENTIFIC:
                stack.push(new Token(Token.Tag.SCIENTIFIC,"scien"));break;
            default:
                throw new MyException(tokenQueue.peek());
        }
    }
}
