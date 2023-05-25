package tech.edev404.rabbitmq.configuration.rabbitMQ.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tech.edev404.rabbitmq.model.dto.UserDTO;


@Service
public class RabbitMQJsonProducer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Value("${rabbit.routing.json.key}")
    private String jsonRoutingKey;

    @Value("${rabbit.exchange.name}")
    private String exchange;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(UserDTO user){
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
        logger.info(String.format("Pojo sent -> %s", user.toString()));
    }

}
