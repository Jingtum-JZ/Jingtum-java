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
import com.jingtum.model.OrderCollection;
import com.jingtum.model.Order;
import java.lang.reflect.Type;
import java.util.List;
public class OrderCollectionDeserializer implements JsonDeserializer<OrderCollection> {
    public OrderCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        if (json.isJsonArray()) {
            Type orderListType = new TypeToken<List<Order>>() {
            }.getType();
            List<Order> order = gson.fromJson(json, orderListType);
            OrderCollection collection = new OrderCollection();
            collection.setData(order);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}