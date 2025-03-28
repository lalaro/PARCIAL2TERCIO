/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;

/**
 *
 * @author laura
 */
public class HTTPServer {
    public static void main(String[] args) throws IOException, URISyntaxException{
        ServerSocket serverSocket= null;
        try{
            serverSocket = new ServerSocket(35000);
        }catch(IOException e){
            System.out.println("Está escuchando por el puerto 35000");
            System.exit(1);
            
        }
        boolean running = true;
        while (running){
            Socket clientSocket = null;
            try{
                System.out.println("Listo para recibir... ");
                clientSocket = serverSocket.accept();
                
            }catch(IOException e){
                System.err.println("Accept failed.");
                System.exit(1);
            }
            OutputStream out = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            String inputLine, outputLine;
            
            boolean isFirstLine = true;
            String file = "";
            while ((inputLine = in.readLine())!=null){
                if (isFirstLine){
                    file = inputLine.split(" ")[1];
                    isFirstLine = false;
                }
                System.out.println("Recibe: "+inputLine);
                if (!in.ready()){
                    break;
                }
            }
            if (file.equals("/")||file.equals("/paginanew")){
                serverStaticFiles("/index.html",out);
            }else if (file.equals("/hello")){
                String response = helloRestService(file, "");
                out.write(response.getBytes());
            }else{
                serverStaticFiles(file,out);
            }
            
            out.close();
            in.close();
            clientSocket.close();
                    
        }
        serverSocket.close();
    }
    
    private static void serverStaticFiles(String filePath,OutputStream out) throws IOException {
        File requestedFile = new File ("classes"+filePath);
        //File requestedFile = new File ("src/main/resources/public"+filePath);
        String outputLine = "";
        if (requestedFile.exists()&&requestedFile.isFile()){
            String contentType = determineContentType(filePath);
            String header = "HTTP/1.1 200 OK\r\n"+
                    "Content-Type: "+ContentType + "\r\n"+
                    "\r\n";
            out.write(header.getBytes());
            try(FileInputStream fileInputStream = new FileInputStream(requestedFile)){
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer))!= -1){
                    out.write(buffer,0,bytesRead);
                }
            }
        } else {
            outputLine = "HTTP/1.1 200 OK\r\n"+
                    "Content-Type: text/html\r\n"+
                    "\r\n"+
                    "<!DOCTYPE html>"
                    +"<html>"
                    +"<body>"
                    + "    <h1>Hola!! Te dice el servidor</h1>"
                    +"</body>"
                    +"</html>";
            out.write(outputLine.getBytes());
        }
        
    }

    private static String determineContentType(String file) {
        if (file.endsWith(".png")) {
            return "png";
        } else if (file.endsWith(".html")) {
            return "html";
        } else {
            return "octet-stream";
        }
    }

    private static String helloRestService(String path, String string) {
        String response = "HTTP/1.1 200 OK\r\n"
                +"Content-Type: aplication/json\r\n"
                +"\r\n"
                +"{\"name\": \"John\", \"age\":30, \"car\":null}";
        return response;
    }
    
    
}
