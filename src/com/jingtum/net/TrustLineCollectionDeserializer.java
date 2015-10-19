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
import com.jingtum.model.TrustLineCollection;
import com.jingtum.model.EffectCollection;
import com.jingtum.model.TrustLine;
import java.lang.reflect.Type;
import java.util.List;
public class TrustLineCollectionDeserializer implements JsonDeserializer<TrustLineCollection> {
    public TrustLineCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
                .create();
        if (json.isJsonArray()) {
            Type trustLineListType = new TypeToken<List<TrustLine>>() {
            }.getType();
            List<TrustLine> trustLine = gson.fromJson(json, trustLineListType);
            TrustLineCollection collection = new TrustLineCollection();
            collection.setData(trustLine);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}
