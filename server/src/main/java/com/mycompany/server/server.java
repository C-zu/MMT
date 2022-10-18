/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.server;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author LEGION
 */

public class server extends javax.swing.JFrame {

    /**
     * Creates new form server
     */
    public server() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Server = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Server.setText("Mở server");
        Server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(Server, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Server, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public String receiveSignal(String s)
    {
        try{
//           program.is = new BufferedReader(new InputStreamReader(program.sserver.getInputStream()));
           s = program.is.readLine();
        }catch (IOException e) {
           s = "QUIT";
        }
        return s;
    }
    
    private void ServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServerActionPerformed
       ServerSocket listener = null;
       System.out.println("Server is waiting to accept user...");
       try {
           listener = new ServerSocket(6920);
           program.sserver = listener.accept();
           program.is = new BufferedReader(new InputStreamReader(program.sserver.getInputStream()));
           program.os = new BufferedWriter(new OutputStreamWriter(program.sserver.getOutputStream()));
           String s = null;
           while (true)
           {
               switch (receiveSignal(s))
               {
                    case "KEYLOG": keylog(); break;
                    case "SHUTDOWN": shutdown(); break;
                    case "REGISTRY": registry(); break;
                    case "TAKEPIC": takepic(); break;
                    case "PROCESS": process(); break;
                    case "APPLICATION": application(); break;
                    case "CHECKSCREEN": checkscreen(); break;
                    case "EXIT": {
                        program.sserver.close();
                        listener.close();
                        return;
                    }
               }
           }
       } catch (IOException e) {
           JOptionPane.showMessageDialog(rootPane, "Không thể mở server");
       } 
    }//GEN-LAST:event_ServerActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Flatlaf Light".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new server().setVisible(true);
//            }
//        });
//    }
    public void keylog()
    {
        String s = null;
    }
    
    public void shutdown()
    {
        try{
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -s -t 0");
            System.exit(0);
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public void application()
    {
        String ss = null;
    }
    
    public void registry()
    {
        String ss = null;
    }
    
    public void process()
    {
        String ss = null;
        while (true)
        {
            switch(receiveSignal(ss))
            {
                case "XEM" ->                 {
                    try {
                        String line = null;
                        Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));                  
                        int soprocess = 0;
                        while(input.readLine() != null){
                            soprocess++;
                        }
                        String soprocess1 = Integer.toString(soprocess);
                        program.os = new BufferedWriter(new OutputStreamWriter(program.sserver.getOutputStream()));
                        program.os.write(soprocess1);
                        program.os.newLine();
                        program.os.flush();
                        Process p1 = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                        BufferedReader input1 = new BufferedReader(new InputStreamReader(p1.getInputStream())); 
                        ObjectOutputStream out = new ObjectOutputStream(program.sserver.getOutputStream());
                        for(int i = 0; (i<soprocess) ;i++) {
                            line = input1.readLine();
                            if (i>4)
                            {
                                for (int u =0; u < line.length()-2;u++)
                                {
                                    if ((line.charAt(u)>64 && line.charAt(u)<=122)&&(line.charAt(u+2)>64 && line.charAt(u+2)<=122) && line.charAt(u+1)==' ')
                                    {
                                        line = line.substring(0,u+1)+"_"+line.substring(u+2,line.length());
                                    }
                                }
                                String[] splitline = line.trim().split("\\s{1,100}");
                                String data[] = {splitline[0],splitline[1],splitline[2],splitline[3],splitline[4]+splitline[5]};
                                out.writeObject(data);
                            }
                        }
                        out.flush();
                    }
                    catch(Exception e)
                    {
                      JOptionPane.showMessageDialog(null,e);
                    } finally {
                    }
                }
                  case "QUIT" -> {
                                        return;
                }
            }
        }
    }
    
    public void takepic()
    {
        String ss = null;
        while (true)
        {
            switch(receiveSignal(ss))
            {
                case "TAKE" ->                 {
                    try{
                        Toolkit toolkit = Toolkit.getDefaultToolkit();
                        Dimension dimensions = toolkit.getScreenSize();
                        Robot robot = new Robot();  // Robot class
                        BufferedImage screenshot = robot.createScreenCapture(new Rectangle(dimensions));
                        ImageIO.write(screenshot,"png", program.sserver.getOutputStream());
                    } catch(Exception ex){
                            JOptionPane.showMessageDialog(null,ex);
                    }
                }
                case "QUIT" ->                 {
                    return;
                }
            }
        }
    }
    
    public void checkscreen()
    {
        String ss = null;
        while (true)
        {
            switch(receiveSignal(ss))
            {
                case "START" ->                 {
                    try{
                        Robot robot = new Robot();
                        Toolkit toolkit = Toolkit.getDefaultToolkit();
                        Dimension d = toolkit.getScreenSize();

                        while (true){
                            ByteArrayOutputStream ous = new ByteArrayOutputStream();
                            BufferedImage img = robot.createScreenCapture(new Rectangle(0,0,(int) d.getWidth(), (int) d.getHeight()));                            ImageIO.write(img, "png", ous);
                            program.sserver.getOutputStream().write(ous.toByteArray());
                            ous.reset();
                        }
                    } catch(Exception ex){
                            JOptionPane.showMessageDialog(null,ex);
                    }
                }
                case "QUIT" ->                 {
                    return;
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Server;
    // End of variables declaration//GEN-END:variables

    void setvisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
