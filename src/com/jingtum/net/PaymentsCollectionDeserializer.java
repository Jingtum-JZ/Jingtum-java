package com.jingtum.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.jingtum.model.PaymentsCollection;
import com.jingtum.model.BalanceCollection;
import com.jingtum.model.Payments;

import java.lang.reflect.Type;
import java.util.List;

public class PaymentsCollectionDeserializer implements JsonDeserializer<PaymentsCollection> {

    public PaymentsCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
                registerTypeAdapter(Payments.class, new PaymentsDeserializer())
                .create();

        if (json.isJsonArray()) {
            Type paymentsListType = new TypeToken<List<Payments>>() {
            }.getType();
            List<Payments> payments = gson.fromJson(json, paymentsListType);
            PaymentsCollection collection = new PaymentsCollection();
            collection.setData(payments);
            collection.setHasMore(false);
            return collection;
        }

        return gson.fromJson(json, typeOfT);
    }
}
