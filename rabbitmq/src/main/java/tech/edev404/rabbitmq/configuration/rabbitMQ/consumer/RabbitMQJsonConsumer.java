package tech.edev404.rabbitmq.configuration.rabbitMQ.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import tech.edev404.rabbitmq.model.dto.UserDTO;

@Service
public class RabbitMQJsonConsumer {

    public static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consume(UserDTO user){
        logger.info(String.format("Pojo recieved -> %s", user.toString()));
    }
    
}
