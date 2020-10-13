package ir.webold.framework.utility;

import ir.webold.framework.config.general.GeneralStatic;
import ir.webold.framework.domain.dto.EventDTO;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
//@ConditionalOnProperty("${rabbit.enable}")
public class ApplicationRabbitmq {
    @Value("${rabbit.queue.name}")
    private String queueName;
    final private List<EventDTO> eventList;
    private final RabbitTemplate rabbitTemplate;

    public ApplicationRabbitmq(RabbitTemplate rabbitTemplate) {
        eventList = new ArrayList<>();
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

    public List<EventDTO> getEvent(String eventType, Boolean isConsumed) {
        List<EventDTO> events= new ArrayList<>();
        eventList.forEach(EventDTO->{
            if (EventDTO.getEventType().equals(eventType)) {
                events.add(EventDTO);
            }
        });
        if (isConsumed)
            eventList.removeAll(events);
        return events;
    }
    public List<EventDTO> getEvent() {
        return eventList;
    }
    @RabbitListener(queues = {"${rabbit.queue.name}"})
    public void receiveMessage(final Message message) {
        eventList.add(EventDTO.builder().body(new String(message.getBody(), StandardCharsets.UTF_8)).eventType(message.getMessageProperties().getType()).build());
    }


}
