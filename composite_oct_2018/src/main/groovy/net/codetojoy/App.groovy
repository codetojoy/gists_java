package net.codetojoy

class App {
    void go() {
        def rows = new Rows().buildRows()
    }

    static void main(def args) {
        System.out.println("TRACER starting ... " + new Date().toString())
        App app = new App()
        app.go()
        System.out.println("TRACER Ready.")
    }
}
