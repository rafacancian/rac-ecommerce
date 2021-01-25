package com.ecommerce.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Slf4j
public class GsonDeserializer<T> implements Deserializer<T> {

    public static String TYPE_CLASS_CONFIG = "";
    private final Gson gson = new GsonBuilder().create();
    private Class<T> type;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String type_config = String.valueOf(configs.get(TYPE_CLASS_CONFIG));
        try {
            this.type = (Class<T>) Class.forName(type_config);
        } catch (ClassNotFoundException e) {
            log.error("Gson deserializer type of {} for deserializer does not exists in the classpath", type_config, e);
            throw new RuntimeException("Gson deserializer type for deserializer does not exists in the classpath" + type_config, e);
        }

    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), type);
    }
}
