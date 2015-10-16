package com.jingtum.net;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
 */
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.jingtum.model.EffectCollection;
import com.jingtum.model.Transaction;
import com.jingtum.model.TransactionCollection;
public class TransactionCollectionDeserializer implements JsonDeserializer<TransactionCollection> {
    public TransactionCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
                .create();
        if (json.isJsonArray()) {
            Type transactionListType = new TypeToken<List<Transaction>>() {
            }.getType();
            List<Transaction> transaction = gson.fromJson(json, transactionListType);
            TransactionCollection collection = new TransactionCollection();
            collection.setData(transaction);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}
