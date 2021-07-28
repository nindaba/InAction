package com.yadlings.topologytest.Domain;

import com.yadlings.topologytest.Constants.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRoleCount {
    private Role role;
    private int count;
}
