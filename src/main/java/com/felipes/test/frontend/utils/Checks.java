package com.felipes.test.frontend.utils;

public class Checks {

    public Boolean isNull(Object... args) {
        for (var arg : args) {
            if (arg == null) return true;

            if (arg instanceof String && (((String) arg).isEmpty() || ((String) arg).isBlank())) {
                return true;
            }

            if (arg instanceof Integer) {
                if (((Integer) arg) == 0) {
                    return true;
                }
            }
        }
        return false;
    }


}