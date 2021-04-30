package com.iesvi.shared.infra.socket;

import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfigurationSocketTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Esta clase se encarga de realizar un test unitario sobre Usuario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigurationSocketTest.class})
public class SocketserverInfraUnitTC extends UnitTestCase {

    @Autowired
    MultiThreadedServerTcp serversocket;

    @Override
    public void setup() {
        super.setup();
    }

    /**
     * Metodo que comprueba si se ha creado un nuevo usuario
     */
    @Test
    public void ShouldSendDataToSockerServer() throws IOException, InterruptedException, ClassNotFoundException {

        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        String mensaje = "";

        //establish socket connection to server
        socket = new Socket(host.getHostName(), 5555);

        //write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");

        PrintWriter writer = new PrintWriter(oos, true);

        for (int i = 0; i < 5; i++) {

            if (i == 4)
                mensaje = "exit";
            else
                mensaje = "Esto es un mensaje muy largo: " + i;

            writer.println(mensaje);

            if (i == 4) {
                writer.flush();
                Thread.sleep(1000);
            }
        }

        //close resources
        oos.close();
        Thread.sleep(100);
    }
}

