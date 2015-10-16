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
import com.jingtum.model.Effect;
import com.jingtum.model.EffectCollection;
public class EffectCollectionDeserializer implements JsonDeserializer<EffectCollection> {
    public EffectCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        if (json.isJsonArray()) {
            Type effectListType = new TypeToken<List<Effect>>() {
            }.getType();
            List<Effect> effect = gson.fromJson(json, effectListType);
            EffectCollection collection = new EffectCollection();
            collection.setData(effect);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}
