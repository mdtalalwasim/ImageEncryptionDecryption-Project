
package com.JavaImageEncryption;

import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Md. Talal Wasim
 */
public class ImageEncryptionDecryptionOperation {
    public static void main(String[] args) {
        System.out.println("Program Started...");
        
        JFrame frame = new JFrame();
        frame.setTitle("Image Encryption Decryption Operation");
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);//frame will appear in center position.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font font = new Font("",Font.BOLD,30);
        //creating Button
        JButton button = new JButton();
        
        button.setText("Open Image");
        button.setFont(font);
        
        
        
        //creating Text Field
        JTextField txtField = new JTextField(10);
        txtField.setFont(font);
        
        //ActionListener for Button...
        button.addActionListener(e->{//lamda function...
            System.out.println("Button Clicked!");
            String txtFromTxtField = txtField.getText();//get the txt form txtField...
            int text= Integer.parseInt(txtFromTxtField);//The value of txtField is String and it converted into int.
            operation(text);
            
            
        }); //using Lamda function
        
        //place all of above things on top of the Layout...
        //
        frame.setLayout(new FlowLayout());
        
        frame.add(button);
        frame.add(txtField);
        frame.setVisible(true);
        
    }

    private static void operation(int key) {
        JFileChooser fileChoser = new JFileChooser();
        fileChoser.showOpenDialog(null); //open at center
        File file = fileChoser.getSelectedFile();
        
        //file FileInputStream...
        try {
            
            FileInputStream fis = new FileInputStream(file);
            
            
            byte []data = new byte[fis.available()];//we dont know the file size... so the file size is equal to the fis available size.
            
            //for read the file
            fis.read(data);//pass a byte array
            //the desired image is stored in data[] arry and converted to byte means image will convert in a byte number....
            int i=0;
            for (byte b : data) {
               System.out.println(b);
               data[i]=(byte)(b^key); //xor
               i++;
            }
            
            //for Write into file...
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
