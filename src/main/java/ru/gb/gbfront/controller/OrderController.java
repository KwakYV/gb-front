package ru.gb.gbfront.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.order.api.OrderGateway;
import ru.gb.api.order.dto.OrderDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderGateway orderGateway;

    @GetMapping
    public String showForm(Model model){
        model.addAttribute("order", OrderDto.builder().build());
        return "order";
    }

    @GetMapping("/list")
    public String getOrderList(Model model) {
        model.addAttribute("orderList", orderGateway.getOrderList());
        return "order-list";
    }

    @GetMapping("/{order_id}")
    public String getOrderById(@PathVariable("order_id") Long orderId, Model model) {
        OrderDto orderDto = orderGateway.getOrder(orderId).getBody();
        model.addAttribute("order", orderDto);
        return "order";
    }

    @PostMapping
    public String handlePost(@RequestBody OrderDto orderDto) {
        orderGateway.handlePost(orderDto);
        return "redirect:/order";
    }


    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id, @RequestParam(name = "productId") Long productId) {
        return "redirect:/order/list";
    }
}
