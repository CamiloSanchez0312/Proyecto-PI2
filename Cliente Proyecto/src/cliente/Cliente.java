/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Cristhian Guzman
 */
public class Cliente implements Runnable{
    
    ObjectInputStream flujoEntradaSocket;
    ObjectOutputStream flujoSalidaSocket;
    Socket Socketcliente;
    
    
    //Pregunta
    String encabezado, pregunta,opcA, opcB, opcC, opcD, opcCorrecta;
    
    public Cliente()
    {
    }
    
    
    public void conectarServidor()
    {
        try {
            Socketcliente = new Socket(InetAddress.getByName("192.168.1.31"),19919);                       
            flujoSalidaSocket = new ObjectOutputStream(Socketcliente.getOutputStream());
            System.err.println("Todo Bien Todo Bonito");
            flujoEntradaSocket = new ObjectInputStream(Socketcliente.getInputStream());
            System.err.println("Todo Bien Todo Bonito");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void pedirPregunta(int pregunta)
    {
        try {
            flujoSalidaSocket.writeObject(pregunta);
            flujoSalidaSocket.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void recibirPregunta()
    {
        

        String[] DatosPregunta;
        DatosPregunta = new String[7];
        
        while(true)
        {

        try 
        {
           DatosPregunta = (String[]) flujoEntradaSocket.readObject();
           for(int a = 0;a < 7; a++)
           {
               System.err.println(DatosPregunta[a]);
           }
        
            
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          
}
