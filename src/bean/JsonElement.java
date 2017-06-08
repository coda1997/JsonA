package bean;

import symbol.Token;

/**
 * Created by 60440 on 2017/6/8.
 */
public class JsonElement extends MyJson {
    private String type;
    private String value;

    public JsonElement(String type, String value) {
        this.type = type;
        this.value = value;
    }
    public JsonElement(Token token){
        type=token.getTag().toString().toLowerCase();
        value=token.getTokenValue();
    }
}
