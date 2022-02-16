package ru.gb.gbfront.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.gb.api.category.api.CategoryGateway;
import ru.gb.api.category.dto.CategoryDto;
import ru.gb.api.manufacturer.api.ManufacturerGateway;
import ru.gb.api.manufacturer.dto.ManufacturerDto;
import ru.gb.api.product.api.ProductGateway;
import ru.gb.api.product.dto.ProductDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductGateway productGateway;
    private final CategoryGateway categoryGateway;
    private final ManufacturerGateway manufacturerGateway;

    public String prepareProductForm(Model model, Long id){
        ProductDto product;
        List<ManufacturerDto> manufacturers = manufacturerGateway.getManufacturerList();
        if (id != null) {
            product =  productGateway.getProduct(id).getBody();
        } else {
            product = ProductDto.builder().build();
        }
        model.addAttribute("product", product);
        model.addAttribute("manufacturers", manufacturers);

        return "product-form";
    }

    public void handlePost(ProductDto product, String categories){
        product.setManufactureDate(LocalDate.now());
        if (!"".equals(categories)){
            Set<CategoryDto> categoryDtoSet = Arrays.stream(categories.split(",")).map(
                    item -> {
                        CategoryDto categoryDto = categoryGateway.getCategory(item.trim()).getBody();
                        return categoryDto;
                    }
            ).collect(Collectors.toSet());
            product.setCategories(categoryDtoSet);
        }

        productGateway.handlePost(product);

    }

}
