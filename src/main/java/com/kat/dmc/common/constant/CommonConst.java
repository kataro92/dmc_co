package com.kat.dmc.common.constant;

public class CommonConst {

    public enum Code {
        IMPORT_CATEGORY_ID_0("IMPORT_CATEGORY_ID","0", "Vật tư"),
        IMPORT_CATEGORY_ID_1("IMPORT_CATEGORY_ID","1", "Thành phẩm"),
        EMP_GENDER_0("EMP_GENDER","0", "--"),
        EMP_GENDER_1("EMP_GENDER","1", "Nam"),
        EMP_GENDER_2("EMP_GENDER","2", "Nữ"),
        EMP_STATUS_0("EMP_STATUS","0", "Hoạt động"),
        EMP_STATUS_1("EMP_STATUS","1", "Khoá"),
        DEPT_STATUS_0("DEPT_STATUS","0", "Hoạt động"),
        DEPT_STATUS_1("DEPT_STATUS","1", "Khoá");

        private String type;
        private String code;
        private String value;

        Code(String type, String code, String value) {
            this.type = type;
            this.code = code;
            this.value = value;
        }

        public String type() {
            return type;
        }
        public String code() {
            return code;
        }
        public String value() {
            return value;
        }
    }
}
