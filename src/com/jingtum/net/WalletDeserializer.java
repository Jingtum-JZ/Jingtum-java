package com.jingtum.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import com.jingtum.model.Wallet;
import com.jingtum.model.BalanceCollection;

import java.lang.reflect.Type;

public class WalletDeserializer implements JsonDeserializer<Wallet> {
    @Override
    public Wallet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
                registerTypeAdapter(BalanceCollection.class, new BalanceCollectionDeserializer())
                .create();

        Wallet wallet = gson.fromJson(json, Wallet.class);

        return wallet;
    }
}
