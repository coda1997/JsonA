package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class ValueS extends Symbol {
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        switch (tokenQueue.peek().getTag()){
            case LB:
                stack.push(new ObjectXS());
                break;
            case LBK:
                stack.push(new ArrayXS());
                break;
            case TRUET:
                stack.push(new Token(Token.Tag.TRUET,"true"));break;
            case FALSET:
                stack.push(new Token(Token.Tag.FALSET,"false"));break;
            case NULLT:
                stack.push(new Token(Token.Tag.NULLT,"null"));break;
            case STRING:
                stack.push(new Token(Token.Tag.STRING,"string"));break;
            case INTEGER:
            case FLOAT:
            case SCIENTIFIC:
                stack.push(new NumberS());
                break;
            default:
                throw new MyException(tokenQueue.peek());
        }
    }
}
