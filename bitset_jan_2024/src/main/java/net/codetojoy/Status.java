package net.codetojoy;

import java.util.*;

public class Status {
    public static final String FIELD_1 = "f1";
    public static final String FIELD_2 = "f2";
    public static final String FIELD_4 = "f4";
    public static final String FIELD_8 = "f8";
    public static final String FIELD_16 = "f16";
    public static final String ERROR = "error";

    BitSet bits;

    Status(int value) {
        bits = new BitSet();
        int index = 0;
        int chopValue = value;
        while (chopValue != 0) {
            if (chopValue % 2 != 0) {
              bits.set(index);
            }
            index++;
            chopValue = chopValue >>> 1;
        }
    }

    protected String getValue(int index) {
        switch (index) {
            case 0:
                return FIELD_1;
            case 1:
                return FIELD_2;
            case 2:
                return FIELD_4;
            case 3:
                return FIELD_8;
            case 4:
                return FIELD_16;
            default:
                return ERROR;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < bits.length(); index++) {
            if (bits.get(index)) {
                String value = getValue(index);
                builder.append(value + ",");
            }
        }
        return builder.toString();
    }
}
