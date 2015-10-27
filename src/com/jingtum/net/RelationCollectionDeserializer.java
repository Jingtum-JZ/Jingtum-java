package com.jingtum.net;
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
import com.jingtum.model.Relation;
import com.jingtum.model.RelationCollection;
/**
 * @author jzhao
 * @version 1.0
 */
public class RelationCollectionDeserializer implements JsonDeserializer<RelationCollection> {
    public RelationCollection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        if (json.isJsonArray()) {
            Type relationListType = new TypeToken<List<Relation>>() {
            }.getType();
            List<Relation> relation = gson.fromJson(json, relationListType);
            RelationCollection collection = new RelationCollection();
            collection.setData(relation);
            return collection;
        }
        return gson.fromJson(json, typeOfT);
    }
}
