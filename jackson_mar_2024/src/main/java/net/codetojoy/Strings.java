
package net.codetojoy;

public class Strings {
    public String addAsInts(String s, String t) {
        var sVal = Integer.parseInt(s);
        var tVal = Integer.parseInt(t);
        var resultVal = sVal + tVal;
        return "" + resultVal;
    }
}
