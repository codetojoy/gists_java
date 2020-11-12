
package net.codetojoy;

public class Bar extends Foo {
    public void bar() {
        final long value = 1000L;
        Foo.mySleep(value);
    }
}
