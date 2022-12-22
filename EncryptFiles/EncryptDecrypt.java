// package EncryptFiles;

import java.util.Scanner;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;


public class EncryptDecrypt{

    public static void operate(int key){
        JFileChooser fChooser=new JFileChooser();
        fChooser.showOpenDialog(null);
        File file=fChooser.getSelectedFile();
        try{
            FileInputStream f=new FileInputStream(file);
            byte[] data=new byte[f.available()];
            f.read(data);
            int i=0;
            for(byte b: data){
                System.out.println(b);
                data[i]=(byte) (b^key);
                i++;
            }
            FileOutputStream fs=new FileOutputStream(file);
            fs.write(data);
            f.close();
            fs.close();
            JOptionPane.showMessageDialog(null, "Done");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello World");
        JFrame frame=new JFrame();
        frame.setTitle("Encryption");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b=new JButton("Encrypt");
        b.setText("Button");
        JTextField textField=new JTextField(10);
        frame.add(textField);
        b.addActionListener(e ->{
            System.out.println("Button Clicked");
            int val=Integer.parseInt(textField.getText());
            operate(val);
        } );
        frame.add(b);
        
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
}

