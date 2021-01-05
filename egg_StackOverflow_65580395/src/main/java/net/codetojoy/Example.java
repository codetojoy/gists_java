
package net.codetojoy;

import java.util.*;
import static java.util.stream.Collectors.toList;

import org.jooq.lambda.Seq;

class Torrent {
    String i;
    String j;
    String k;
    
    Torrent(String i, String j, String k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public String toString() {
        return String.format("i: %s j: %s k: %s", i, j, k);
    }
}

class Element {
    int i;
    String text() { return "" + i; }
    Element(int i) { this.i = i; } 
}

public class Example {
    public static void main(String[] args) {
        Seq<Element> elements1 = Seq.of(new Element(1), new Element(2));
        Seq<Element> elements2 = Seq.of(new Element(3), new Element(4));
        Seq<Element> elements3 = Seq.of(new Element(5), new Element(6));
   
        List<Torrent> torrents = Seq.zip(elements1, elements2, elements3, 
                                        (x,y,z) -> 
                                            new Torrent(x.text(), y.text(), z.text()))
                                    .collect(toList()); 

        for (Torrent t : torrents) {
            System.out.println("TRACER " + t.toString());
        }
        System.out.println("Ready.");
    }
}

