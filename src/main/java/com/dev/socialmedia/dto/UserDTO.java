package com.dev.socialmedia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    Long id;
    String username;
    String displayName;
    String avatar;
    String bio;
}
