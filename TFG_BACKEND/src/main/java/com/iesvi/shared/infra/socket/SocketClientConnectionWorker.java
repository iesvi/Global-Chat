package com.iesvi.shared.infra.socket;

import com.iesvi.gestionUsuario.application.UsuarioServiceImpl;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.utils.JsonMapper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class SocketClientConnectionWorker implements Runnable {

    private UsuarioServiceImpl usuarioServiceImpl;

    protected Socket clientSocket = null;
    protected String clientID = null;

    private InputStream entrada;
    private OutputStream salida;

    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;

    public SocketClientConnectionWorker(Socket clientSocket, String clientID, UsuarioServiceImpl usuarioServiceImpl) {
        this.clientSocket = clientSocket;
        this.clientID = clientID;
        this.usuarioServiceImpl = usuarioServiceImpl;

        try {
            this.clientSocket.setReceiveBufferSize(1024);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            entrada = clientSocket.getInputStream();
            salida = clientSocket.getOutputStream();

            flujoEntrada = new DataInputStream(entrada);

            System.out.println(clientID + ". Waiting....");

            byte[] buffer = new byte[1024];

            while (true) {

                String mensaje = flujoEntrada.readUTF();

                switch (mensaje) {
                    case "recibirTodos":
                        ArrayList<UsuarioDTO> usuarioDTOS;

                        usuarioDTOS = (ArrayList<UsuarioDTO>) usuarioServiceImpl.findAll();
                        DataOutputStream dataOutputStream = new DataOutputStream(salida);
                        String lista = JsonMapper.fromJavaToJson(usuarioDTOS);
                        dataOutputStream.writeUTF(lista);
                        dataOutputStream.flush();

                        break;
                }

            }

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    public void Send(String text) {
        try {

            salida = clientSocket.getOutputStream();

            flujoSalida = new DataOutputStream(salida);

            flujoSalida.flush();
            flujoSalida.writeUTF(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Recibir() {
        try {

            entrada = clientSocket.getInputStream();

            flujoEntrada = new DataInputStream(entrada);

            String mensaje = flujoEntrada.readUTF();

            System.out.println(this.clientID + ". Valor recibido: " + mensaje);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

