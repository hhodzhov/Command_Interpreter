package general;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import exceptions.*;

public class CommandInterpreter {

	private Map<String, Command> commandsToInterpret;
	private static Map<String, DataOutputStream> users;
	private static int clientCounter = 1;
	private static ExecutorService executors;

	public CommandInterpreter(Map<String, Command> commandsToInterpret) {
		this.commandsToInterpret = commandsToInterpret;
	}

	public String startInterpretation(String expression) {
		expression = expression.trim();
		String command;
		if (expression.indexOf(' ') == -1) {
			command = expression;
		} else {
			command = expression.substring(0, expression.indexOf(' '));
		}
		Command typeOfCommand;

		if (commandsToInterpret.containsKey(command)) {
			typeOfCommand = commandsToInterpret.get(command);
		} else {
			throw new CommandNotFoundException("There is no such command!");
		}

		return typeOfCommand.interpretCommand(expression);
	}

	public void startServer() throws IOException {

		ServerSocket server = new ServerSocket(1234);
		System.err.println("Server started, waiting for client...");
		users = new HashMap<>();
		executors = Executors.newCachedThreadPool(); 

		Thread thread = new Thread(() -> {
			while (true) {
				Socket client = null;
				try {
					client = server.accept();
					executors.submit(handleClientConnection(client));
				} catch (IOException ioException) {
					Logger.getLogger(CommandInterpreter.class.getName()).log(Level.SEVERE, null, ioException);
				}
				System.out.println("Client " + clientCounter++ + " connected");
			}
		});

		thread.start();

	}

	private Runnable handleClientConnection(Socket client) {
		Thread connection = new Thread(() -> {

			DataInputStream in = null;
			try {
				in = new DataInputStream(client.getInputStream());
			} catch (IOException ioException) {
				Logger.getLogger(CommandInterpreter.class.getName()).log(Level.SEVERE, null, ioException);
			}
			DataOutputStream out = null;
			try {
				out = new DataOutputStream(client.getOutputStream());
			} catch (IOException ioException) {
				Logger.getLogger(CommandInterpreter.class.getName()).log(Level.SEVERE, null, ioException);
			}
			String nameOfClient = null;
			try {
				nameOfClient = in.readUTF();
				System.out.println("Name of new client : " + nameOfClient);
			} catch (IOException ioException) {
				Logger.getLogger(CommandInterpreter.class.getName()).log(Level.SEVERE, null, ioException);
			}
			users.put(nameOfClient, out);
			waitForMessages(nameOfClient, in);
		});

		return connection;
	}

	private void waitForMessages(String nameOfClient, DataInputStream in) {
		while (true) {

			String clientMessage = "", serverMessage = "";
			try {
				clientMessage = in.readUTF();
				serverMessage = startInterpretation(clientMessage);
			} catch (IOException ioException) {
				Logger.getLogger(CommandInterpreter.class.getName()).log(Level.SEVERE, null, ioException);
			} catch (CommandNotFoundException cmd) {

				sendToSelectedClient(nameOfClient, cmd.getMessage());
			} catch (IllegalArgumentException illegalArgumentException) {

				sendToSelectedClient(nameOfClient, illegalArgumentException.getMessage());
			} catch (TypeNotFoundException typeNotFoundException) {

				sendToSelectedClient(nameOfClient, typeNotFoundException.getMessage());
			} catch (VariableNotFoundException variableNotFoundException) {

				sendToSelectedClient(nameOfClient, variableNotFoundException.getMessage());
			} catch (OperationNotAllowedException noSuchOperation) {
				
				sendToSelectedClient(nameOfClient, noSuchOperation.getMessage());
			}
			System.out.println("Client " + "'" + nameOfClient + "' said : " + clientMessage);

			sendToSelectedClient(nameOfClient, serverMessage);
		}
	}

	private void sendToSelectedClient(String nameOfClient, String serverMessage) {
		if (users.containsKey(nameOfClient)) {
			DataOutputStream toSend = users.get(nameOfClient);

			try {
				toSend.writeUTF(serverMessage);
				toSend.flush();
			} catch (IOException e) {
				Logger.getLogger(CommandInterpreter.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}
