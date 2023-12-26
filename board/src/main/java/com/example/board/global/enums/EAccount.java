package com.example.board.global.enums;

public class EAccount {

    public enum EAccountRole {
        ADMIN("ADMIN"),
        USER("USER");

        private String role;

        private EAccountRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return this.role;
        }

    }
}
