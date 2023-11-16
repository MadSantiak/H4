package dk.tec.maso.server;

public class ClientMessage {
	String message;
	boolean newMessage;
	
	synchronized public String getMessage() {
		return message;
	}
	synchronized public void setMessage(String message) {
		this.message = message;
	}
	synchronized public boolean isNewMessage() {
		return newMessage;
	}
	synchronized public void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}
	
	
}
