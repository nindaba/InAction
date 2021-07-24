package com.yadlings.Serializers;

public enum UserTopologies {
    USER_RECEIVER{
        @Override
        public String toString() {
            return "user_received";
        }
    },USER_SPLITTER,ADD_USER_TO_LOCAL,ADD_USER_TO_ADMIN
}
