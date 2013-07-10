package slave.Api;

import java.io.IOException;
import java.net.UnknownHostException;
import slave.Model.Slave;

public class Api {
    private MediatorSocket mediatorSocket;
    private SlaveSocket slaveSocket;
    private Slave slave;

    public Api(String ip, int port,Slave slave) throws UnknownHostException, IOException {
        this.mediatorSocket = new MediatorSocket(ip, port, slave);
        this.slave = slave;
    }
    
    public void start() throws IOException{
        registerChannel();
      //  startConnectionWithMaster();
    } 

    private void registerChannel() throws IOException {
        this.mediatorSocket.registerChannel();
    }
    
    private void startConnectionWithMaster() throws IOException{
        this.slaveSocket = new SlaveSocket(this.slave);
        this.slaveSocket.execute();
    }
    
}
