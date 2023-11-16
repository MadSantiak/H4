package dk.tec.maso.player;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

import dk.tec.maso.object.RectangleObject;

public class PlayerClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            RectangleObject board = new RectangleObject(writer);
            board.setBounds(200, 200, 400, 400);
            board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            board.setTitle("Red vs Green");

            board.setVisible(true);

            ServerListener listen = new ServerListener(reader, board);
            Thread playerUpdateThread = new Thread(listen);
            playerUpdateThread.start();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
