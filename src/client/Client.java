package client;

import java.io.*;
import java.net.*;

public class Client {   
    private static final int PORT = 3333; 
    private static final String HOST = "localhost";       
    
    public static void main(String[] args) {
        
        if (args.length == 0) 
            throw new RuntimeException("Print message");

        try {
            InetAddress hostAddress = InetAddress.getByName(HOST); 
            System.out.println("Client start with HOST: " + HOST );
            Socket socket = new Socket(hostAddress, PORT); 
 
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом. 
            InputStream ins = socket.getInputStream();
            OutputStream outs = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream dins = new DataInputStream(ins);
            DataOutputStream douts = new DataOutputStream(outs);

            String message = args[0];
            String json = "{\"name\": \"Tom\", \"message\": " + message + "}";
            douts.writeUTF(json);
            douts.flush();
            
            String json1 = dins.readUTF(); // ждем пока сервер отошлет текст.
            System.out.println("JSON from server:" + json1);
            
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }     
}