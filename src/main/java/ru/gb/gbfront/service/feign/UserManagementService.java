package ru.gb.gbfront.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.api.security.dto.UserDto;

@FeignClient(url = "localhost:8484/api/v1/user", name = "userServiceClient")
public interface UserManagementService {

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody UserDto userDto);
}
