package net.codetojoy;

import net.codetojoy.annotation.Bar;
import net.codetojoy.annotation.Foo;
import net.codetojoy.annotation.Restrict;

import java.util.List;

@Restrict
public class ExampleController {

    @Foo
    public List<String> getEmployees() {
        return List.of("Alice", "Bob", "Charlie");
    }

    @Bar
    public List<String> getProducts() {
        return List.of("Widget", "Gadget", "Gizmo");
    }
}
