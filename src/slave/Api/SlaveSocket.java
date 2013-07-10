package slave.Api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import slave.messages.Command;
import slave.messages.MasterResponse;
import slave.Model.Slave;

public class SlaveSocket {

    private ServerSocket serverSocket = null;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Slave slave;

    public SlaveSocket(Slave slave) throws IOException {
        this.slave = slave;
        this.serverSocket = new ServerSocket(this.slave.getPort());
        initializeConnection();
    }

    private void initializeConnection() throws IOException {
        this.socket = this.serverSocket.accept();
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void execute() throws IOException {
        Command command;
        while ((command = readCommand()) != null) {
            registerCommand(command);
            sendResponseToMaster();
        }
    }

    private Command readCommand() throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(this.reader.readLine(), Command.class);
    }

    private void sendResponseToMaster() {
        MasterResponse masterResponse = this.slave.getResponse();
        Gson gson = new Gson();
        this.writer.println(gson.toJson(masterResponse));
    }

    private void registerCommand(Command command) {
        this.slave.registerCommand(command);
    }
}
