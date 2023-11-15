package dk.tec.maso.server;

public class ClientMessage {
	String message;
	boolean newMessage;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isNewMessage() {
		return newMessage;
	}
	public void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}
	
	
}
