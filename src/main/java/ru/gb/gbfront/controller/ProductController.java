package ru.gb.gbfront.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.category.api.CategoryGateway;
import ru.gb.api.category.dto.CategoryDto;
import ru.gb.api.manufacturer.api.ManufacturerGateway;
import ru.gb.api.manufacturer.dto.ManufacturerDto;
import ru.gb.api.product.api.ProductGateway;
import ru.gb.api.product.dto.ProductDto;
import ru.gb.gbfront.config.TokenHandler;
import ru.gb.gbfront.service.ProductService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductGateway productGateway;
    private final ProductService productService;

    @Resource(name = "tokenHandler")
    TokenHandler tokenHandler;

    @GetMapping("/all")
    public String getProductList(Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenHandler.getToken());
        model.addAttribute("products", productGateway.getProductList());
        return "product-list";
    }

    @GetMapping()
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        return productService.prepareProductForm(model, id);
    }

    @PostMapping
    public String saveProduct(ProductDto product, @RequestParam("category") String category) {
        productService.handlePost(product, category);
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
