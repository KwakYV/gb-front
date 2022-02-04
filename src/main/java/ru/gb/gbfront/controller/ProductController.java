package ru.gb.gbfront.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.product.api.ProductGateway;
import ru.gb.api.product.dto.ProductDto;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductGateway productGateway;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productGateway.getProductList());
        return "product-list";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        ProductDto product;
        if (id != null) {
            product = (ProductDto) productGateway.getProduct(id).getBody();
        } else {
            product = ProductDto.builder().build();
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping
    public String saveProduct(ProductDto product) {
        product.setManufactureDate(LocalDate.now());
        productGateway.handlePost(product);
        return "redirect:/product/all";
    }

//    @GetMapping("/addToCart")
//    public String addToCart(@RequestParam(name = "id", required = false) Long id){
//        Product product = productService.findById(id);
//        List<Product> products = new ArrayList<>();
//        products.add(product);
//        if (cart == null){
//            cart = Cart.builder()
//                    .number(cartService.maxNumber() + 1)
//                    .products(products)
//                    .build();
//        } else {
//            cart = cartService.findById(cart.getId());
//            cart.getProducts().add(product);
//        }
//        cart = cartService.saveOrUpdate(cart);
//        return "redirect:/product/all";
//    }


    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        productGateway.deleteById(id);
        return "redirect:/product/all";
    }

    @GetMapping("/{productId}")
    public String info(Model model, @PathVariable("productId") Long id){
        ProductDto product;
        if (id != null) {
            product = (ProductDto) productGateway.getProduct(id).getBody();
        } else {
            return "redirect:/product/all";
        }
        model.addAttribute("product", product);
        return "product-info";
    }
}
