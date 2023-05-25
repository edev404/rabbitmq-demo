package tech.edev404.rabbitmq.configuration.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbit.exchange.name}")
    private String exchange;

    @Value("${rabbit.routing.key}")
    private String routingKey;

    @Value("${rabbit.routing.json.key}")
    private String jsonRoutingKey;

    //Spring bean for rabbitMQ queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //Spring bean for rabbitMQ queue
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    //Spring bean for rabbitMQ topicExhange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //Spring bean for rabbitMQ bind
    @Bean
    public Binding bind(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //Spring bean for rabbitMQ json bind
    @Bean
    public Binding jsonBind(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    //MessageConverter
    @Bean
    public MessageConverter jsonConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //ConnectionFactory for rabbitMQ
    @Bean 
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }
    //RabbitTemplate for rabbitMQ

    //RabbitMQ admin 
    
}
