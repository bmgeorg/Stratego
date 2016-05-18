package view;

import java.net.Socket;

public interface OpponentFinderDelegate {
	public void foundOpponent(Socket socket);
}
