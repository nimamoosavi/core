package ir.webold.framework.utility;

import ir.webold.framework.config.general.GeneralStatic;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("${rabbit.enable}")
public class ApplicationRabbitmq {
    @Value("${rabbit.queue.name}")
    private String queueName ;
    private final RabbitTemplate rabbitTemplate;

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

    public void sendMessageExchange(String exchangeName,String message,String eventType){
        MessageProperties prop = new MessageProperties();
        prop.setType(eventType);
        Message msg = new Message(message.getBytes(),prop);
        rabbitTemplate.send(exchangeName,"",msg);

    }
    public void sendMessageQueue(String gueueName,String message,String eventType){
        MessageProperties prop = new MessageProperties();
        prop.setType(eventType);
        Message msg = new Message(message.getBytes(),prop);
        rabbitTemplate.send(gueueName,msg);
    }

    

}
