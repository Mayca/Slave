
package slave.Model;

import Slave.messages.Message;
import Slave.messages.SlaveRequestMessage;
import Slave.messages.SlaveResponseMessage;
import slave.messages.Command;
import slave.messages.MasterResponse;

public class Slave {
    private String name;
    private String ip;
    private int port;

    public Slave(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
       
    public Message getRequest() {
        return new SlaveRequestMessage(this.name, this.ip, this.port);        
    }

    public void registerResponse(SlaveResponseMessage slaveResponseMessage) {
        this.port = slaveResponseMessage.getPort();
    }
    
    public MasterResponse getResponse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void registerCommand(Command command) {
        
    }
    
}
