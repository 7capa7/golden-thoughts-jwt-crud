package com.capa.jwtAuthThoughts.goldenThoughts.entity;

import com.capa.jwtAuthThoughts.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_GoldenThought")
public class GoldenThought {
    @Id
    @GeneratedValue
    private Integer id;
    private String value;
    private boolean isDone;
    @ManyToOne()
    private User user;

}
