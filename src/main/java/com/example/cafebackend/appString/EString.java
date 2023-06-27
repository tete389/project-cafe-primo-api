package com.example.cafebackend.appString;

public enum EString {


    WAIT_PAYMENT("Wait Payment"),
    CANCEL_ORDER("Cancel Order"),
    PAYMENT_SUCCESS("Payment Success"),
    EDITING("Editing"),
    CREATING("Creating"),
    NONE("none"),

    ALL("All"),

    COLLECT_POINT("Collect Points"),

    SPEND_POINT("Spend Points")
    ;


    public final String name;

    public String getValue() {
        return this.name;
    }

    public static EString get(String name) {
        for(EString e : EString.values()) {
            if(e.name.equals(name)) return e;
        }
        return null;
    }

    EString(String name) {
        this.name = name;
    }

}
