package utils;

import bean.MyJson;
import symbol.Token;

import java.util.*;

/**
 * Created by 60440 on 2017/6/8.
 */
public class JsonUtils {
    public static String jsonFind(ArrayList<Token> tokens,String path){
        Queue<Token> tokenQueue = new LinkedList<>(tokens);
        StringTokenizer stringTokenizer = new StringTokenizer(path,"/");
        if(!tokenQueue.isEmpty()){
            if (tokenQueue.poll().getTag().equals(Token.Tag.LB)){

                return findJsonObejct(tokenQueue,stringTokenizer);
            }
        }else {
            return null;
        }
        return null;
    }
    private static String findJsonObejct(Queue<Token> tokenQueue,StringTokenizer stringTokenizer){
        if(tokenQueue.peek().getTag().equals(Token.Tag.LB)){
            tokenQueue.poll();
        }
        if (!stringTokenizer.hasMoreTokens()){
            ArrayList<Token> tokens  = new ArrayList<>();
            String type=null;
            int i = 0;
            tokens.add(tokenQueue.poll());
            if(!tokenQueue.peek().getTag().equals(Token.Tag.COMMA)){
                tokens.add(tokenQueue.poll());
                if(tokenQueue.peek().getTag().equals(Token.Tag.LB)){
                    type="json object";
                }else if(tokenQueue.peek().getTag().equals(Token.Tag.LBK)){
                    type="json array";
                }
                do{
                    Token token = tokenQueue.poll();
                    switch (token.getTag()){
                        case LB:
                        case LBK:
                            i++;
                            tokens.add(token);
                            break;
                        case RB:
                        case RBK:
                            i--;
                            tokens.add(token);
                            break;
                        case SEMI:
                            tokens.add(token);
                            break;
                        default:
                            tokens.add(token);
                            break;
                    }
                }while (i>0);
            }


            StringBuffer stringBuffer = new StringBuffer();
            for (Token token:tokens
                 ) {
                stringBuffer.append(token.getTokenValue());
            }
            if(type==null){
                type=tokens.get(0).getTag().toString().toLowerCase();
            }
            stringBuffer.append("\n type: "+type);
            return stringBuffer.toString();
        }
            String path = getNextStringToken(stringTokenizer);
            if(path.indexOf('[')!=-1){
                String index = path.substring(path.indexOf('[')+1,path.length()-1);
                path="\""+path.substring(0,path.indexOf('['))+"\"";
                int arrayIndex = Integer.parseInt(index)-1;
                while (!tokenQueue.peek().getTokenValue().equals(path)){
                    skipToken(tokenQueue);

                }
                tokenQueue.poll();
                tokenQueue.poll();
                tokenQueue.poll();
                for(int i =0;i<arrayIndex;i++){
                    skipToken(tokenQueue);
                }
            }else {
                if(tokenQueue.isEmpty()){
                    return null;
                }
                path="\""+path+"\"";
                while (!tokenQueue.peek().getTokenValue().equals(path)){
                    skipToken(tokenQueue);
                }
            }
        return findJsonObejct(tokenQueue,stringTokenizer);
    }

    private static void skipToken(Queue<Token> tokenQueue){
        if (tokenQueue.isEmpty()){
            return;
        }
        int i = 0;
        while (!(tokenQueue.peek().getTag().equals(Token.Tag.COMMA)&&i==0)){
                switch (tokenQueue.poll().getTag()){
                    case LB:
                    case LBK:
                        i++;
                        break;
                    case RB:
                    case RBK:
                        i--;
                        break;
                    default:
                        break;
                }
        }
        tokenQueue.poll();//quit comma
    }
    private static String getNextStringToken(StringTokenizer stringTokenizer){
        if(stringTokenizer.hasMoreTokens()){
            return stringTokenizer.nextToken();
        }else {
            return null;
        }
    }
}
