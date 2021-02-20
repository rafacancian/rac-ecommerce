package com.ecommerce.gson;

import com.ecommerce.model.Correlate;
import com.ecommerce.model.Message;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class MessageAdapter implements JsonSerializer<Message>, JsonDeserializer<Message> {
    @Override
    public JsonElement serialize(Message message, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", context.serialize(message.getPayload().getClass().getName()));
        jsonObject.add("correlate", context.serialize(message.getCorrelate()));
        jsonObject.add("payload", context.serialize(message.getPayload()));
        return jsonObject;
    }

    @Override
    public Message deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        try {
            var jsonMessage = json.getAsJsonObject();
            var payloadType = jsonMessage.get("type").getAsString();
            var correlate = (Correlate) context.deserialize(jsonMessage.get("correlate"), Correlate.class);
            //update to accept a class for name of a authorize list
            var payload = context.deserialize(jsonMessage.get("payload"), Class.forName(payloadType));
            return new Message(correlate, payload);
        } catch (ClassNotFoundException e) {
            log.error("Error to deserialize object json: {} , type: {} , context: {}", json, type, context);
            throw new JsonParseException("Error to deserialize object " + e);
        }
    }
}
