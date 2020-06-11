package com.example.orchestrator;

// import java.util.concurrent.CountDownLatch;

import com.example.order.Order;
import com.example.order.Order.Status;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class Receiver {

    private static final String EXCHANGE = "spring-bootexchange";
    private static final String EXCHANGE_ROUTER_KEY = "foo.bar.baz";
    private static final String HOST_URL = "http://localhost:8080";
    private static final String PAYMENT_PAY = "/payment/pay";
    private static final String RESTAURANT_COOK = "/restaurant/cook";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private WebClient webClient = WebClient.create(HOST_URL);

    // private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(Order order) {

        switch(order.getStatus()) {
            case CREATED: schedulePayment(order); break;
            case TO_PAY: sendToPayment(order); break;
            case PAID: sendToRestaurant(order); break;
            case COOKING: updateStatus(order); break;
            case COOKED: sendTodelivery(order); break;
            default:
                // Returns to Queue
                // rabbitTemplate.convertAndSend(EXCHANGE, EXCHANGE_ROUTER_KEY, order);
                break;
        }
        // latch.countDown();
    }
    
    private void updateStatus(Order order) {
        log.info("Updating order: ", order.toString());
    }

    private void schedulePayment(Order order) {
        order.setStatus(Status.TO_PAY);
        rabbitTemplate.convertAndSend(EXCHANGE, EXCHANGE_ROUTER_KEY, order);
        log.info("Scheduled payment: ", order.toString());
    }

    private void sendToPayment(Order order) {
        order.setStatus(Status.PAYING);
        dispatch(order, PAYMENT_PAY); 
        log.info("Dispatched to payment: ", order.toString());
    }
    
    private void sendToRestaurant(Order order) {
        dispatch(order, RESTAURANT_COOK); 
        log.info("Dispatched to restaurant: ", order.toString());
    }

    private void sendTodelivery(Order order) {
        log.info("Dispatched to delivery: ", order.toString());
    }

    // public CountDownLatch getLatch() {
    //     return latch;
    // }

    public void dispatch(Order order, String uri) {
        
        this.webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(order), Order.class)
            .retrieve()
            .bodyToMono(Order.class)
            .block();
    }
}