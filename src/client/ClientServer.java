package client;

/*Client
      Server
        Instancja Client może połączyć się z instancją Server. Połącznie jest reprezentowane za pomocą obiektu klasy
        Connection.
        Zakładamy że klient używa tylko połączeń które sam utworzył
        Klasa Connection umożliwia wysłanie żądań i otrzymywanie odpowiedzi. Utwórz odpowiednio klasę Request i Response.
        Usługa która realizuje serwer polega na odwróceniu kolejności liter zawartości żądania oraz zmianie liter z dużych
        na małe i małych na duże.
        Mamy jedną instancję serwera i 5 instancji klientów */

public class ClientServer {

    public static void main(String[] args) {

        Server server = new Server();

        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        Client client4 = new Client();
        Client client5 = new Client();

        //utworzyc watek serwera
        // utworzyc 5 watkow klienckich
        //watki klienckie wysylaja zadania do serwera

        Thread t1 = new Thread(() -> {
            Connection c1 = client1.connect(server);
            Response response = c1.execute(new Request("qAz"));
            System.out.println(response.getPayload());
        });
        t1.start();
    }

}

class Client extends Thread {
    Connection connect(Server server) {
        return new Connection(server);
    }
}

class Server {
//powinien miec pule watkow
    public Response service(Request request) {
       //utworzyc nowy watek z puli
       //wydelegowac tam probe zadania
        String reversed = reverse(request.getPayload());
        String flipped = flip(reversed);
        return new Response(flipped);
    }

    private String flip(String reversed) {
        char[] array = reversed.toCharArray();
        String result = null;

        char data[] = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            if (Character.isLowerCase(array[i])) {
                char charUppercase = Character.toUpperCase(array[i]);
                data[i] = charUppercase;
            }
            if (Character.isUpperCase(array[i])) {
                char lowerCase = Character.toLowerCase(array[i]);
                data[i] = lowerCase;

            }

        }
        return new String(data);
    }

    private String reverse(String payload) {
        return new StringBuilder(payload).reverse().toString();
    }

}

class Connection {
    private Server server;

    public Connection(Server server) {
        this.server = server;
    }

    public Response execute(Request request) {
        return server.service(request);
    }
}

class Request {
    private String payload;


    public Request(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}

class Response {
    private String payload;

    public Response(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}


