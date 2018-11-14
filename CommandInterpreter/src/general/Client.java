package general;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		 Socket socket = new Socket("localhost", 1234);
	        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
	        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	        Thread fromServer = new Thread(() -> {

	            String serverMessage = "";
	            while (true) {
	                try {
	                    serverMessage = dataInputStream.readUTF();
	                } catch (IOException ioException) {
	                	Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ioException);
	                }
	                System.out.println(serverMessage);
	            }
	        });
	        fromServer.start();
	        System.out.println("Enter your name");
	        dataOutputStream.writeUTF(bufferedReader.readLine());
            dataOutputStream.flush();
            System.out.println("Connection is successful. You can write commands now!");
            
	        //To server
            String toSend = "";
	        do  {
	        	toSend = bufferedReader.readLine();
	            dataOutputStream.writeUTF(toSend);
	            dataOutputStream.flush();
	        }while(!toSend.equals("log out"));
	        
	        System.exit(0);
	        socket.close();
		
	}
}
