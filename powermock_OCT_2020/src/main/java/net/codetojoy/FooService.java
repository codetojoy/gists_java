
package net.codetojoy;

public class FooService {

    // return Foo or possibly Bar
    public Foo getFoo(int id) {
        Foo result = null;

        if (id % 2 == 0) {
            result = FooDAO.getFoo(id);
        } else {
            result = FooDAO.getBar(id);
        }

        return result;
    } 
}
