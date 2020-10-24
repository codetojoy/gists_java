

class MyClient {
    def adr
    def port
    def c
    // def r
    def w

    MyClient (String adr, int port) {
        this.adr=adr;
        this.port=port;
        try {
            c=new Socket(adr, port);
            // r = new BufferedReader(new InputStreamReader(c.getInputStream()));
            w = new PrintWriter(new OutputStreamWriter(c.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

def msgToServer(String msg) {
    def result = false

    try {
        println "TRACER sending msg: " + msg 
        w.write(msg + "\n");
        w.flush();
        // c.flush();
    } catch (Exception e1) {
        System.err.println "caught exception : " + ex.message
    }

    if(msg.toLowerCase().equals("bye")) {
        print("I am out, bye");
        result = true
        try {
            c.close();
            // r.close();
            w.close();
        } catch (IOException e) {
            System.err.println "caught exception : " + ex.message
        }
    }

    return result
}

}

// main 

int port=7777;
String msg =null;

Scanner sc = new Scanner(System.in);
def c = new MyClient("localhost", port);

System.out.println("Write something to server");

def isDone = false
while (! isDone) {
    String s = sc.next();
    System.out.println("TRACER s: " + s);
    isDone = c.msgToServer(s);
}

println "Ready."
