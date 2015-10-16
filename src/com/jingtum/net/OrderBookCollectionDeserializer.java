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
import com.google.gson.reflect.TypeToken;
import com.jingtum.model.OrderBookCollection;
import com.jingtum.model.OrderBook;
import java.lang.reflect.Type;
import java.util.List;
public class OrderBookCollectionDeserializer implements JsonDeserializer<OrderBookCollection> {
    public OrderBookCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        if (json.isJsonArray()) {
            Type orderBookListType = new TypeToken<List<OrderBook>>() {
            }.getType();
            List<OrderBook> orderBook = gson.fromJson(json, orderBookListType);
            OrderBookCollection collection = new OrderBookCollection();
            collection.setData(orderBook);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}
