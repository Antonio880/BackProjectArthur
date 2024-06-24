package com.prototype.ProjectArthur.DTO;

public record UserDTO(
    Long id,
    String email,
    String username,
    String password,
    String role,
    Long roomId
) {
}
