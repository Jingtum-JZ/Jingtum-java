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
import com.jingtum.model.Wallet;
import com.jingtum.model.BalanceCollection;
import com.jingtum.model.EffectCollection;
import com.jingtum.model.PaymentCollection;
import com.jingtum.model.TransactionCollection;
import com.jingtum.model.TrustLineCollection;
import com.jingtum.model.OrderCollection;
import com.jingtum.model.OrderBookCollection;
import java.lang.reflect.Type;
public class WalletDeserializer implements JsonDeserializer<Wallet> {
    @Override
    public Wallet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(BalanceCollection.class, new BalanceCollectionDeserializer())
                .registerTypeAdapter(PaymentCollection.class, new PaymentCollectionDeserializer())
                .registerTypeAdapter(OrderCollection.class, new OrderCollectionDeserializer())
                .registerTypeAdapter(OrderBookCollection.class, new OrderBookCollectionDeserializer())
                .registerTypeAdapter(TrustLineCollection.class, new TrustLineCollectionDeserializer())
                .registerTypeAdapter(TransactionCollection.class, new TransactionCollectionDeserializer())
                .registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
                .create();
        Wallet wallet = gson.fromJson(json, Wallet.class);
        return wallet;
    }
}
