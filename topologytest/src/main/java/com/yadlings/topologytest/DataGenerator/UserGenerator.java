package com.yadlings.topologytest.DataGenerator;

import com.yadlings.topologytest.Constants.Role;
import com.yadlings.topologytest.Domain.User;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
@Log4j2
public class UserGenerator {
    public static Random random = new Random();
    @Data
    public static class UserData{
        public User getUser(){
            return new User(random.nextInt(20)+11,randomString(),randomString(), randomRole(), new Date());
        }
        public List<User> getUsers() {
            return
                    range(1,random.nextInt(10)+3).mapToObj(
                    num->{
                        return new User(num,randomString(),randomString(), randomRole(),new Date());
                    }
                    ).collect(Collectors.toList());
        }
        public String randomString(){
            var len = random.nextInt(12)+3;
            var string = "";
            while (len>0){
                var guess = random.nextInt(25)+65;
                string = string+ (char) guess;
                len--;
            }
            return string;
        }
        public Role randomRole(){
            var i = random.nextInt(3)+1;
            switch (i){
                case 1:
                    return Role.ADMIN;
                case 2:
                    return Role.STUDENT;
                default:
                    return Role.EMPLOYEE;

            }
        }
    }
}
