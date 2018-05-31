package com.kat.dmc.common.constant;

public class SQLErrorConst {
    public enum Error {
        user_username_uindex("user_username_uindex", "Tên đăng nhập bị trùng");

        private String error;
        private String message;

        Error(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String error() {
            return error;
        }
        public String message() {
            return message;
        }
    }
}
