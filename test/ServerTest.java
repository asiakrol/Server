package client;

import org.junit.jupiter.api.Test;

import java.rmi.ServerError;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerTest {
    @org.junit.Test

    public void service() throws Exception {

        Server server = new Server();

        Request request = new Request("testoWy");
        Response response = server.service(request);
        System.out.println(response.getPayload());
        assertTrue(response.getPayload().equals("YwOTSET"));
    }




}