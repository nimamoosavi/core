package ir.webold.framework.utility;

import ir.webold.framework.config.general.GeneralStatic;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConditionalOnProperty("${rabbit.enable}")
public class ApplicationRabbitmq {
    @Value("${rabbit.queue.name}")
    private String queueName;
    private final RabbitTemplate rabbitTemplate;
    private final Map<String, List<Object>> EVENTS = new HashMap<>();

    public ApplicationRabbitmq(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public Declarables fanoutBindings() {
        Queue queue = new Queue(queueName, true);
        FanoutExchange fanoutExchange = new FanoutExchange(GeneralStatic.RABBIT_LOG_OUT_EXCHANGE);
        return new Declarables(
                queue,
                fanoutExchange,
                BindingBuilder.bind(queue).to(fanoutExchange)
        );
    }

    public void sendMessageExchange(String exchangeName, String message, String eventType) {
        MessageProperties prop = new MessageProperties();
        prop.setType(eventType);
        Message msg = new Message(message.getBytes(), prop);
        rabbitTemplate.send(exchangeName, "", msg);
    }

    public void sendMessageQueue(String gueueName, String message, String eventType) {
        MessageProperties prop = new MessageProperties();
        prop.setType(eventType);
        Message msg = new Message(message.getBytes(), prop);
        rabbitTemplate.send(gueueName, msg);

    }

    public List<Object> getEvent(String eventType) {
        return EVENTS.get(eventType);
    }

    @RabbitListener(queues = {"${rabbit.queue.name}"})
    public void receiveMessage(final Message message) {
        if (EVENTS.get(message.getMessageProperties().getType()) != null) {
            EVENTS.get(message.getMessageProperties().getType()).add(new String(message.getBody(), StandardCharsets.UTF_8));
        } else {
            List<Object> objects = new ArrayList<>();
            objects.add(new String(message.getBody(), StandardCharsets.UTF_8));
            EVENTS.put(message.getMessageProperties().getType(), objects);
        }
    }


}
