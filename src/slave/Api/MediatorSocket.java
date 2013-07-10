package slave.Api;

import Slave.messages.Message;
import Slave.messages.SlaveResponseMessage;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import slave.Model.Slave;

public class MediatorSocket {

    private Socket socket = null;
    private PrintWriter writer;
    private BufferedReader reader;
    private Slave slave;

    public MediatorSocket(String ip, int port, Slave slave) throws UnknownHostException, IOException {
        initializeConnection(ip, port);
        this.slave = slave;
    }

    private void initializeConnection(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void registerChannel() throws IOException{
        sendRequest();
        waitResponse();
    }
    private void sendRequest() {
        sendHeader();
        sendSlaveRequest();
    }
    
     private void waitResponse() throws IOException {
        Gson gson = new Gson();
        SlaveResponseMessage slaveResponseMessage = gson.fromJson(this.reader.readLine(), SlaveResponseMessage.class);
        this.slave.registerResponse(slaveResponseMessage);
    }

    private void sendHeader() {
        Gson gson = new Gson();
        this.writer.println(gson.toJson("SLAVE"));
    }

    private void sendSlaveRequest() {
        Message request = this.slave.getRequest();
        Gson gson = new Gson();
        this.writer.println(gson.toJson(request));
    }   
}
