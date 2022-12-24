import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

class Server{
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    Server(){
        try{
            serverSocket=new ServerSocket(1234);
            System.out.println("Server is ready");
            socket=serverSocket.accept();
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    void startReading(){
        Runnable r1=()->{
            System.out.println("Reader started");
            while(true){
                try{
                String msg=br.readLine();
                if(msg.equals("exit")){
                    System.out.println("Terminated");
                    socket.close();
                    serverSocket.close();
                    System.exit(0);

                    // break;
                }
                System.out.println("Client: "+msg);
                }
                catch(Exception e){
                    System.out.println("System closed");
                    System.exit(0);

                    // e.printStackTrace();
                }
            }
        };
        new Thread(r1).start();;
    }
    void startWriting(){
        System.out.println("Writer started");
        Runnable r2=()->{
            while(true){
                try{
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        new Server();
    }
}