package ru.gb.gbfront.service.security;


import org.springframework.cloud.openfeign.FeignClient;
import ru.gb.api.security.dto.UserDto;
import ru.gb.gbfront.entity.security.AccountUser;

import java.util.List;

public interface UserService {


    UserDto register(UserDto userDto);

    UserDto update(UserDto userDto);

    UserDto findById(Long id);

    List<UserDto> findAll();

    AccountUser findByUsername(String username);

    void deleteById(Long id);
}
