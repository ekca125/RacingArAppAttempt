package net.ekcapaper.racingar.nakama;

import com.heroiclabs.nakama.AbstractSocketListener;
import com.heroiclabs.nakama.Client;
import com.heroiclabs.nakama.DefaultClient;
import com.heroiclabs.nakama.Session;
import com.heroiclabs.nakama.SocketClient;
import com.heroiclabs.nakama.SocketListener;

import net.ekcapaper.racingar.keystorage.KeyStorageNakama;

import java.security.Key;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class NakamaNetworkManager {
    private Client client;
    private Session session;
    private SocketClient socketClient;

    public NakamaNetworkManager(){
        client = null;
        session = null;
        socketClient = null;
    }

    // client
    public void makeClient(){
        this.client = new DefaultClient(
                KeyStorageNakama.getServerKey(),
                KeyStorageNakama.getGrpcAddress(),
                KeyStorageNakama.getGrpcPort(),
                true);
    }

    public boolean checkClient(){
        return client != null;
    }

    // session
    public void makeSession(String email, String password) throws ExecutionException, InterruptedException {
        this.session = client.authenticateEmail(email,password).get();
    }

    public boolean checkSession(){
        return session != null;
    }

    public void makeSocketClient(Runnable disconnectCallback, Runnable successCallback) throws ExecutionException, InterruptedException {
        this.socketClient = client.createSocket(
                KeyStorageNakama.getWebSocketAddress(),
                KeyStorageNakama.getWebSocketPort(),
                true
        );
        SocketListener socketListener = new AbstractSocketListener() {
            @Override
            public void onDisconnect(Throwable t) {
                super.onDisconnect(t);
                disconnectCallback.run();
            }
        };
        socketClient.connect(session, socketListener).get();
        successCallback.run();
    }

    public boolean checkSocketClient(){
        return socketClient != null;
    }

    public boolean checkOnline(){
        return checkClient() && checkSession() && checkSocketClient();
    }
}
