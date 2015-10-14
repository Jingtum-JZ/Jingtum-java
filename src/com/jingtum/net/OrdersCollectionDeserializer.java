package com.jingtum.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import com.jingtum.model.OrdersCollection;
import com.jingtum.model.Orders;

import java.lang.reflect.Type;
import java.util.List;

public class OrdersCollectionDeserializer implements JsonDeserializer<OrdersCollection> {

    public OrdersCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        if (json.isJsonArray()) {
            Type ordersListType = new TypeToken<List<Orders>>() {
            }.getType();
            List<Orders> orders = gson.fromJson(json, ordersListType);
            OrdersCollection collection = new OrdersCollection();
            collection.setData(orders);
            collection.setHasMore(false);
            return collection;
        }

        return gson.fromJson(json, typeOfT);
    }
}