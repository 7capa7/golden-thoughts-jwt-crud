package com.capa.jwtAuthThoughts.goldenThoughts.utlis;

import com.capa.jwtAuthThoughts.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoldenThoughtResponse {
    private Integer id;
    private String value;
    private boolean isDone;
    private UserDTO user;
}
