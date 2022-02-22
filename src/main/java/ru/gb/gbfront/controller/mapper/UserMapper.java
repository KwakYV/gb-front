package ru.gb.gbfront.controller.mapper;

import org.mapstruct.Mapper;
import ru.gb.api.security.dto.UserDto;
import ru.gb.gbfront.entity.security.AccountUser;

@Mapper
public interface UserMapper {
    AccountUser toAccountUser(UserDto userDto);
    UserDto toUserDto(AccountUser accountUser);
}
