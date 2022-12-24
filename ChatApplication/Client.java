import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    Client(){
        try{
            System.out.println("Sending request to server");
            socket=new Socket("127.0.0.1", 1234);
            System.out.println("Connection done");
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
                    System.out.println(" Server Terminated");
                    socket.close();
                    System.exit(0);
                    
                }
                System.out.println("Server: "+msg);
                }
                catch(Exception e){
                    System.out.println("Client terminated");
                    System.exit(0);
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
        new Client();
    }
}
