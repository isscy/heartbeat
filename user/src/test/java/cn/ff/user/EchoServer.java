package cn.ff.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {


    /**
     * 多线程
     */
    public static void main(String[] args) throws IOException {

        try (ServerSocket ss = new ServerSocket(23300)) {
            int sort = 1;

            while (true) {
                Socket incoming = ss.accept();

                Runnable r = new ThreadedEchoHandler(incoming, sort);
                Thread t = new Thread(r);
                System.out.println("第 " + sort + " 号view由 " + t.getName() + " 负责" );
                t.start();
                sort ++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

class ThreadedEchoHandler implements Runnable {
    private Socket incoming;
    private int daeling = 0;

    public ThreadedEchoHandler(Socket socket) {
        this.incoming = socket;
    }

    public ThreadedEchoHandler(Socket socket, int sortNo) {
        this(socket);
        this.daeling = sortNo;
    }

    public void run() {
        String name = Thread.currentThread().getName();
        try {
            try {
                InputStream is = incoming.getInputStream();
                OutputStream os = incoming.getOutputStream();
                Scanner in = new Scanner(is);
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("Hello, y sort is " + daeling + " , i'm " + name );
                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    pw.println("Echo: " + line);
                    if ("BYE".equals(line.trim().toUpperCase()))
                        done = true;
                }
            } finally {
                incoming.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }




// 单线程
    /*public static void main(String[] args) throws IOException {

        try (ServerSocket ss = new ServerSocket(23300)){
            try (Socket incoming = ss.accept()){
                InputStream is = incoming.getInputStream();
                OutputStream os = incoming.getOutputStream();
                try (Scanner scanner = new Scanner(is)){
                    PrintWriter pw = new PrintWriter(os, true);
                    pw.println("Hello! ");
                    boolean done = false;
                    while (!done &&  scanner.hasNextLine()){
                        String line = scanner.nextLine();
                        pw.println("Echo: " + line);
                        if ("BYE".equals(line.trim()))
                            done = true;
                    }

                }

            }


        }

    }*/
}
