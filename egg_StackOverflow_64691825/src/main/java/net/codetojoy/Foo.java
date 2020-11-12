
package net.codetojoy;

public class Foo {
    public static void mySleep(long value) {}

    public void foo() {
        final long value = 1000L;
        Foo.mySleep(value);
    }
}
