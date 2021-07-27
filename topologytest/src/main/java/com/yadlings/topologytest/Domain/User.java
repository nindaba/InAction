package com.yadlings.topologytest.Domain;

import com.yadlings.topologytest.Constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private int id;
    private String name;
    private String pass;
    private Role role;
    private Instant instant;
}
