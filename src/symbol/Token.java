package symbol;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by 60440 on 2017/5/24.
 */
public class Token extends Symbol{
    private final Tag tag;
    private final String tokenValue;

    private int line;
    private int position;

    @Override
    public void detect(Stack<Symbol> stack, Queue<Token> tokenQueue) {
        if(tag.equals(tokenQueue.peek().getTag())){
            tokenQueue.poll();
        }
    }

    public enum Tag{
        INTEGER,FLOAT,STRING,SCIENTIFIC,TRUET,FALSET,NULLT,LB,RB,LBK,RBK,SEMI,COMMA,DOLLAR
    }

    public Token(Tag tag,String value) {
        this.tag=tag;
        tokenValue=value;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public Tag getTag(){
        return tag;
    }

    public boolean tokenEqual(Token token){
        return tag.equals(token.getTag());
    }
    public boolean valueEqual(Token token){
        return tokenEqual(token)&&tokenValue.equals(token.getTokenValue());
    }

    public String getTokenValue() {
        return tokenValue;
    }

    @Override
    public String toString() {

        return "Token{" +
                "tag=" + tag +
                ", tokenValue='" + tokenValue + '\'' +
                ", line=" + line +
                ", position=" + position +
                '}';
    }

    public String getErrMss(){
        return "line "+line+" position "+position+": expected<"+tokenValue+">";
    }
}
