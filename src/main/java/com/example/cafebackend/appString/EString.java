package com.example.cafebackend.appString;

public enum EString {

    PAYMENT("Payment"),
    MAKING("Making"),
    RECEIVE("Receive"),
    SUCCESS("Success"),
    EDITING("Editing"),
    CREATING("Creating"),
    NONE("None"),
    CANCEL("Cancel"),
    KEEP("Keep"),
    // ALL("ALL"),
     All("All"),


    COLLECT_POINT("Collect Points"),
    WAIT_COLLECT("Wait Collect"),
    SPEND_POINT("Spend Points"),
    WAIT_SPEND("Wait Spend"),

    NAME("NAME"),
    Name("Name");

    public final String name;

    public String getValue() {
        return this.name;
    }

    public static EString get(String name) {
        for (EString e : EString.values()) {
            if (e.name.equals(name))
                return e;
        }
        return null;
    }

    EString(String name) {
        this.name = name;
    }

}
