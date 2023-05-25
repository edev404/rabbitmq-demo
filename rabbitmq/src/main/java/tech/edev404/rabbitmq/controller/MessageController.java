package tech.edev404.rabbitmq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.edev404.rabbitmq.configuration.rabbitMQ.publisher.RabbitMQJsonProducer;
import tech.edev404.rabbitmq.configuration.rabbitMQ.publisher.RabbitMQProducer;
import tech.edev404.rabbitmq.model.dto.UserDTO;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;
    private RabbitMQJsonProducer rabbitMQJsonProducer;

    

    public MessageController(RabbitMQProducer rabbitMQProducer, RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> handleSendMessage(@RequestParam("message") String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
    }

    @GetMapping("/publish/json")
    public ResponseEntity<UserDTO> handleSendJsonMessage(){
        //Just an example, let's think that we got the user as requestBody
        UserDTO user = UserDTO.builder().id(1).firstname("first").lastname("lastname").build();
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    
}
