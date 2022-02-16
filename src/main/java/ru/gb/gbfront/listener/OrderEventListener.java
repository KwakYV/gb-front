package ru.gb.gbfront.listener;

import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.gb.api.events.OrderEvent;
import ru.gb.gbfront.config.JmsConfig;
import ru.gb.gbfront.service.MailService;

@Component
@AllArgsConstructor
public class OrderEventListener {
    private final JmsTemplate jmsTemplate;
    private final MailService mailService;

    @JmsListener(destination = JmsConfig.ORDER_CHANGED)
    public void consume(OrderEvent orderEvent) {
        System.out.println(orderEvent.getOrderDto());
        mailService.sendMail("kvakyur@gmail.com", "Change order", orderEvent.getOrderDto().toString());
    }
}
