package task02;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

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
        
            try{
                response = ois.readUTF();

                if(response != null){
                    String[] value = response.split(" ");
                    String requestID = value[0];
                    String num = value[1];
                    System.out.println(requestID);
                    System.out.println(num);

                    String[] numList = num.split(",");
                    LinkedList<Integer> intList = new LinkedList<>();

                    int total =0;
                    float average = 0;
                    for(String combine: numList){
                        try{
                            int numbers = Integer.parseInt(combine);
                            total = total + numbers;
                            intList.add(numbers);

                        }catch(NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    average =(float)total/intList.size();
                    System.out.println(average);
                    }
                }

        }catch(UnknownHostException e){
            System.out.println("Server not found: " + e.getMessage());
        }catch(IOException e){
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    }
}

    