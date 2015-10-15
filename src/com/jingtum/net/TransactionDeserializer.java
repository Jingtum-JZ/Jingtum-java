package com.jingtum.net;

import java.lang.reflect.Type;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jingtum.model.EffectCollection;
import com.jingtum.model.Payment;
import com.jingtum.model.Transaction;

public class TransactionDeserializer implements JsonDeserializer<Transaction> {
    @Override
    public Transaction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        		.registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
        		.create();

        Transaction payment = gson.fromJson(json, Transaction.class);

        return payment;
    }
}
