package org.example.cxf;


import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.example.impl.HelloServiceImpl;

public class Server {
    public static void main(String[] args) {
        String address = "http://localhost:9090/services/hello";
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(HelloServiceImpl.class);
        factory.setAddress(address);
        factory.create();
        System.out.println("WSDL: " + address + "?wsdl");
    }
}