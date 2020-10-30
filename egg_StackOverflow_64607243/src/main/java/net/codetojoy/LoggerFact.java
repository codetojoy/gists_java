
package net.codetojoy;

class Logger {
    public void info(String s) {} 
}

public class LoggerFact {
    public static Logger getLogger(Class clazz, String methodName) {
        return new Logger();
    }
}
