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

                String requestID = null;
                float average = 0;

                if(response != null){
                    String[] value = response.split(" ");
                    requestID = value[0];
                    String num = value[1];
                    System.out.println(requestID);
                    System.out.println(num);

                    String[] numString = num.split(",");
                    LinkedList<Integer> intLL = new LinkedList<>();

                    int total =0;

                    for(String individual: numString){
                        try{
                            int numbers = Integer.parseInt(individual);
                            total = total + numbers;
                            intLL.add(numbers);

                        }catch(NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    average =(float)total/(intLL.size());
                    }
                    System.out.println(average);

                }

                oos.writeUTF(requestID);
                oos.flush();

                oos.writeUTF("Khairul Haizad Bin Mahadi");
                oos.flush();

                oos.writeUTF("1khairulh@gmail.com");
                oos.flush();

                oos.writeFloat(average);
                oos.flush();

                boolean reply = ois.readBoolean();

                if(reply == true){
                    System.out.println("SUCCESS");
                }else if(reply == false){
                    System.out.println("FAILED");
                    ois.readUTF();
                }

                os.close();
                is.close();


        }catch(UnknownHostException e){
            System.out.println("Server not found: " + e.getMessage());
        }catch(IOException e){
            System.out.println("I/O error: " + e.getMessage());
        }
    }
    }
}

    