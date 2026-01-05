package org.example;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.example.impl.HelloServiceImpl;

/**
 * Classe de démarrage du serveur SOAP CXF
 */
public class SoapServerLauncher {

    public static void main(String[] args) {

        String serviceAddress = "http://localhost:8080/services/hello";

        JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
        serverFactory.setServiceClass(HelloServiceImpl.class);
        serverFactory.setAddress(serviceAddress);
        serverFactory.create();

        System.out.println("Service SOAP démarré");
        System.out.println("WSDL disponible à l’adresse : " + serviceAddress + "?wsdl");
    }
}
