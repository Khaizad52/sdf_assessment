package task02;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

public class Client {

    public static void main(String[] args)throws IOException {
        
        String response = null;
        String hostname = "task02.chuklee.com";
        int port = 80;

        try(Socket socket = new Socket(hostname, port)){
            
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
        
            ois.readUTF();
            try{
                response = ois.readUTF();
            }catch(EOFException e){

            }

            if(response != null){
                String[] value = response.split(" ");
                String requestID = value[0];
                String num = value[1];
                System.out.println(requestID);
                System.out.println(num);
            }

            

        }catch(UnknownHostException e){
            System.out.println("Server not found: " + e.getMessage());
        }catch(IOException e){
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
    