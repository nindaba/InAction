package com.yadlings.topologytest.Domain;

import com.yadlings.topologytest.Constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleCount {
    private Role role;
    private int count;
}
