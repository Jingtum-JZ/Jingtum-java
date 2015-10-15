package com.jingtum.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jingtum.model.OrderBookCollection;
import com.jingtum.model.OrderBookResult;

import java.lang.reflect.Type;

public class OrderBookResultDeserializer implements JsonDeserializer<OrderBookResult> {
    @Override
    public OrderBookResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        		.registerTypeAdapter(OrderBookCollection.class, new OrderBookCollectionDeserializer())
        		.create();

        OrderBookResult orderBookResult = gson.fromJson(json, OrderBookResult.class);

        return orderBookResult;
    }
}