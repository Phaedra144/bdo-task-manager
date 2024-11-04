package com.bdo.taskmanager.dto;

import java.util.List;


public record UserDto(String fullName, String password, String email, AddressDto address, List<TaskDto> tasks) {

}
