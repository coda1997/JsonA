package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class ElementXS extends Symbol{
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        switch (tokenQueue.peek().getTag()){
            case LB:
            case LBK:
            case TRUET:
            case FALSET:
            case NULLT:
            case STRING:
            case INTEGER:
            case FLOAT:
            case SCIENTIFIC:
                stack.push(new ElementYS());
                stack.push(new ValueS());
                break;
            default:
                throw new MyException(tokenQueue.peek());
        }
    }
}
