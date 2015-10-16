package com.jingtum.net;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
 */
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jingtum.model.EffectCollection;
import com.jingtum.model.Payment;
import java.lang.reflect.Type;
public class PaymentDeserializer implements JsonDeserializer<Payment> {
    @Override
    public Payment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
    		throws JsonParseException {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        		.registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
        		.create();
        Payment payment = gson.fromJson(json, Payment.class);
        return payment;
    }
}