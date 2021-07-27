package com.yadlings.topologytest.Utils;

import com.yadlings.topologytest.DataGenerator.UserGenerator;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordEncryptor {
    public String encrypt(String pass){
        return "*************";
    }
    public String decrypt(String pass){
        return new UserGenerator.UserData().randomString();
    }
}
