package com.webold.core.utility;

import com.webold.core.domain.dto.EventDTO;
import com.webold.core.config.general.GeneralStatic;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
//@ConditionalOnProperty("${rabbit.enable}")
public class ApplicationRabbitmq {
    private static final Long EXPIRE_JWT_REDIS_TIME = 100000L;

    @Value("${rabbit.queue.name}")
    private String queueName;
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ApplicationRedis applicationRedis;

    public ApplicationRabbitmq(RabbitTemplate rabbitTemplate, ApplicationEventPublisher applicationEventPublisher, ApplicationRedis applicationRedis) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
        this.applicationRedis = applicationRedis;
    }

    @Bean
    private Declarables fanoutBindings() {
        Queue queue = new Queue(queueName, true);
        /*FanoutExchange fanoutExchange = new FanoutExchange(GeneralStatic.RABBIT_LOG_OUT_EXCHANGE);
        return new Declarables(
                queue,
                fanoutExchange,
                BindingBuilder.bind(queue).to(fanoutExchange)
        );*/
        return null;
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


    @RabbitListener(queues = {"${rabbit.queue.name}"})
    private void receiveMessage(final Message message) {
        applicationEventPublisher.publishEvent(new EventDTO(message.getMessageProperties().getType(), new String(message.getBody(), StandardCharsets.UTF_8)));
    }


    @EventListener()
    public void logOut(EventDTO eventDTO) {
        applicationRedis.setIn(eventDTO.getBody(), eventDTO.getEventType(), EXPIRE_JWT_REDIS_TIME);
    }

}
