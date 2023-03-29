
package net.codetojoy;

import java.util.*;
import java.text.SimpleDateFormat;

class LogUtil {
    static String getLogPrefix() {
        StringBuilder builder = new StringBuilder();
        builder.append("TRACER ");
        builder.append(" (" + Thread.currentThread().getId() + ") ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String strDate = formatter.format(new Date());
        builder.append(strDate + " ");
        return builder.toString();
    }
}

