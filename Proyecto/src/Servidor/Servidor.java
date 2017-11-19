/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;


import java.util.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristhian Guzman
 */
public class Servidor implements Runnable{
    
    Pregunta P1,P2,P3,P4,P5;
    ArrayList <Pregunta> listaPreguntas;
    ArrayList <Pregunta> nuevaListaPreguntas;
    ObjectInputStream flujoEntradaArchivo;
    ObjectOutputStream flujoSalidaArchivo;
    ObjectOutputStream flujoSalidaSocket;
    ObjectInputStream flujoEntradaSocket;
    
    
    ServerSocket servidorSocket;
    Socket SockClienteConect;
    
    public Servidor()
    {
        P1 = new Pregunta();
        P2 = new Pregunta();
        P3 = new Pregunta();
        P4 = new Pregunta();
        P5 = new Pregunta();
        P1.setEncabezado("ksdaaslkmas");
        listaPreguntas = new ArrayList<>();
        guardarPreguntas();
    }
    
    
    public void guardarPreguntas()
    {
        listaPreguntas.add(P1);
        listaPreguntas.add(P2);
        listaPreguntas.add(P3);
        listaPreguntas.add(P4);
        listaPreguntas.add(P5);  
    }
    
    public void enviarObjeto()
    {
        try 
        {
            flujoSalidaArchivo = new ObjectOutputStream(new FileOutputStream("D:\\Users\\Cristhian Guzman\\Documents\\NetBeansProjects\\Proyecto\\archivo.txt"));
            flujoSalidaArchivo.writeObject(listaPreguntas);
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cargarObjeto()
    {
        try {
            
            flujoEntradaArchivo = new ObjectInputStream(new FileInputStream("D:\\Users\\Cristhian Guzman\\Documents\\NetBeansProjects\\Proyecto\\archivo.txt"));
            nuevaListaPreguntas = (ArrayList<Pregunta>)flujoEntradaArchivo.readObject();
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ClassNotFoundException ex2){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex2);
        }
                
    }
    
    
    public void conectarCliente()
    {
        try {
            servidorSocket = new ServerSocket(19919,4);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        int a = 0;
        while(a <= 3)
        {
        try {
            
            SockClienteConect = servidorSocket.accept();
            flujoEntradaSocket = new ObjectInputStream(SockClienteConect.getInputStream());
            flujoSalidaSocket = new ObjectOutputStream(SockClienteConect.getOutputStream());flujoSalidaSocket.flush();
            System.out.println("Se ha conectado un Cliente");
            
            a++;
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }
    
    
    
    public void escucharPregunta()
    {
        int numPregunta=0;
        while(true)
        {
        try {
            numPregunta = Integer.parseInt((String)flujoEntradaSocket.readObject());
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        enviarPregunta(numPregunta);
        }
    }
    
    
    public void enviarPregunta(int noPregunta)
    {
        String[] DatosPregunta;
        DatosPregunta = new String[7];
        
        DatosPregunta[0] = nuevaListaPreguntas.get(noPregunta).getEncabezado();
        DatosPregunta[1] = nuevaListaPreguntas.get(noPregunta).getPregunta();
        DatosPregunta[2] = nuevaListaPreguntas.get(noPregunta).getOpcionA();
        DatosPregunta[3] = nuevaListaPreguntas.get(noPregunta).getOpcionB();
        DatosPregunta[4] = nuevaListaPreguntas.get(noPregunta).getOpcionC();
        DatosPregunta[5] = nuevaListaPreguntas.get(noPregunta).getOpcionD();
        DatosPregunta[6] = nuevaListaPreguntas.get(noPregunta).getOpcionCorrecta();
        
        try {
            flujoSalidaSocket.writeObject(DatosPregunta);

            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
       




    

    

    
    
    
    
    
