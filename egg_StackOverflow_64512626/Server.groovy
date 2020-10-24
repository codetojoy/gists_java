
def port = 7777

try {
    def sers = new ServerSocket(port);
    def client = sers.accept();
    
    def reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    // def writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    
    def clientIsOnline = true;
    
    while (clientIsOnline) {
        print("Connection from client: " + client);
        
        while((msg = reader.readLine()) != null) {
            println "From client > " + msg

            if(msg.toLowerCase().equals("bye")) {
                print("Client left, server closed");
                clientIsOnline=false;
            }
        }
        sers.close();
        reader.close();
        // writer.close();
    }
    
} catch(Exception e) {
    print("Error starting  server: ");
    e.printStackTrace();
}
