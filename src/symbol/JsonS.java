package symbol;

import myException.MyException;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/6/5.
 */
public class JsonS extends Symbol {
    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) throws MyException {
        Token token=tokenQueue.peek();
        switch (token.getTag()){
            case LB:
                stack.push(new ObjectXS());
                break;
            case LBK:
                stack.push(new ArrayXS());
                break;
            case DOLLAR:
                // TODO: 2017/6/5  
                return;
            default:
                throw new MyException(token);

        }
    }
}
