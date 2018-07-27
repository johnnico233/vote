package vote.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDecDateSerializer extends JsonDeserializer<Date> {
    public final static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try{
            return format.parse(p.getText());
        }catch (ParseException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
