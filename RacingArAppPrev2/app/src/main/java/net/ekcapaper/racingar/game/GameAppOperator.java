package net.ekcapaper.racingar.game;

import com.heroiclabs.nakama.Client;
import com.heroiclabs.nakama.DefaultClient;
import com.heroiclabs.nakama.Session;

import net.ekcapaper.racingar.keystorage.KeyStorageNakama;

import java.util.concurrent.ExecutionException;

import nakama.com.google.common.util.concurrent.ListenableFuture;

public class GameAppOperator {
    private Client client;
    private Session session;
    public GameAppOperator(){
        String serverKey = KeyStorageNakama.getServerKey();
        String nodeAddress = KeyStorageNakama.getNodeAddress();
        int portNumber = KeyStorageNakama.getPortNumber();

        client = new DefaultClient(serverKey,nodeAddress,portNumber,true);
    }

    public boolean authEmail(String email, String password)  {
        try {
            session = client.authenticateEmail(email, password).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return session != null;
    }

    public Client getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }
}
