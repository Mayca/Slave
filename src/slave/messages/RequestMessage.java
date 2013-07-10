package Slave.messages;

public abstract class RequestMessage extends Message {
    
    public RequestMessage(String channel) {
        super(channel);
    }
}
