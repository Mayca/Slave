package slave.Model;

public class Channel {
    private Slave slave;

    public Channel(Slave slave) {
        this.slave = slave;
    }

    public Slave getSlave() {
        return slave;
    }
    
    
}
