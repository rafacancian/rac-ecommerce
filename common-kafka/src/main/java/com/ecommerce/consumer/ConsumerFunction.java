package com.ecommerce.consumer;

import com.ecommerce.model.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerFunction<T> {

    void parse(final ConsumerRecord<String, Message<T>> record);
}
