package com.hipposupermecado.validate;

import java.util.regex.Pattern;

public class PatternEmail {

    public boolean isEmail (String email) {
        Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );

        if(EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
            return true;
        }else{
            return false;
        }
    }
}
