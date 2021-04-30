package com.iesvi.shared.infra.socket;

import com.iesvi.gestionUsuario.application.UsuarioServiceImpl;
import com.iesvi.shared.application.Dto;
import com.iesvi.shared.domain.socket.SocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

@Component
public class MultiThreadedServerTcp implements SocketServer, Runnable {

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    protected int serverPort = 5555;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;

    HashMap<Integer, SocketClientConnectionWorker> clientsConnections = new HashMap<>();

    public MultiThreadedServerTcp() {
    }

    public MultiThreadedServerTcp(Integer port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }

            Integer clientId = clientsConnections.size() + 1;
            clientsConnections.put(clientId, new SocketClientConnectionWorker(clientSocket, "ClienteID: " + clientId, usuarioServiceImpl));

            new Thread(clientsConnections.get(clientId)).start();

            System.out.println("SockerServer. New connection received. ClienteID:" + clientId);
        }

        System.out.println("Server Stopped.");
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
            System.out.println("Server-Socket Stopped");
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            System.out.println("Server-Socket Started on " + this.serverSocket.toString());

        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }

    @Override
    public void Send(Integer clientId, String texto) {
        if (!clientsConnections.containsKey(clientId)) {
            System.out.println("SocketServer. El clienteID:" + clientId + " no existe!!");
            return;
        }

        SocketClientConnectionWorker client = clientsConnections.get(clientId);

        client.Send(texto);
    }

    @Override
    public void Send(Integer clientId, Dto object) {
        if (!clientsConnections.containsKey(clientId)) {
            System.out.println("SocketServer. El clienteID:" + clientId + " no existe!!");
            return;
        }

        SocketClientConnectionWorker client = clientsConnections.get(clientId);

        //Deserailizar object to String....
        String deserializedText = object.toString();  //TODO: Esto hay que cambiarlo...
        client.Send(deserializedText);
    }
}