package com.jingtum.net;
/**
 * @author jzhao
 * @version 1.0
 */
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.jingtum.model.PaymentCollection;
import com.jingtum.model.EffectCollection;
import com.jingtum.model.Payment;
import java.lang.reflect.Type;
import java.util.List;
public class PaymentCollectionDeserializer implements JsonDeserializer<PaymentCollection> {
    public PaymentCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Payment.class, new PaymentDeserializer())
                .registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
                .create();
        if (json.isJsonArray()) {
            Type paymentListType = new TypeToken<List<Payment>>() {
            }.getType();
            List<Payment> payment = gson.fromJson(json, paymentListType);
            PaymentCollection collection = new PaymentCollection();
            collection.setData(payment);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}
