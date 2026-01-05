package org.example.cxf;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.example.impl.HelloServiceImpl;
import org.example.security.UTPasswordCallback;
import org.apache.wss4j.common.ConfigurationConstants;

import java.util.HashMap;
import java.util.Map;

public class SecureServer {
    public static void main(String[] args) {
        // Configuration de l'intercepteur WS-Security
        Map<String, Object> inProps = new HashMap<>();
        inProps.put(ConfigurationConstants.ACTION, ConfigurationConstants.USERNAME_TOKEN);
        inProps.put(ConfigurationConstants.PASSWORD_TYPE, "PasswordText");
        inProps.put(ConfigurationConstants.PW_CALLBACK_REF, new UTPasswordCallback(
                Map.of("student", "secret123")
        ));

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        // Création du serveur sécurisé
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(HelloServiceImpl.class);
        factory.setAddress("http://localhost:9091/services/hello-secure");  // Port différent
        Server server = factory.create();

        // Ajout de l'intercepteur
        server.getEndpoint().getInInterceptors().add(wssIn);

        System.out.println("Serveur sécurisé démarré !");
        System.out.println("WSDL: http://localhost:9091/services/hello-secure?wsdl");
        System.out.println("Utilise UsernameToken : student / secret123");

        // Garde vivant
        synchronized (SecureServer.class) {
            try { SecureServer.class.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}