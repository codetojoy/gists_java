
void emitLog(List<String> items) {
    for (String item: items) {
        IO.println("item: " + item);
    }
}

void main() { 
    emitLog(List.of("Mozart", "Chopin", "Brahms"));
    IO.println("TRACER 5150: ready.");
}
