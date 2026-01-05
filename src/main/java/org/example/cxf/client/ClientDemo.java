package org.example.cxf.client;

import org.example.api.HelloService;
import jakarta.xml.ws.Service;

import javax.xml.namespace.QName;
import java.net.URL;

public class SoapClientExample {

    public static void main(String[] args) throws Exception {

        // Adresse du WSDL exposé par le service SOAP
        URL wsdlLocation = new URL("http://localhost:9090/services/hello?wsdl");

        // QName du service (namespace + nom du service, à vérifier dans le WSDL)
        QName qname = new QName("http://api.example.org/", "HelloService");

        // Création du proxy SOAP
        Service soapService = Service.create(wsdlLocation, qname);
        HelloService helloPort = soapService.getPort(HelloService.class);

        // Appel de l’opération SayHello
        String greeting = helloPort.sayHello("ClientJava");
        System.out.println("Réponse SayHello : " + greeting);

        // Appel de l’opération FindPerson
        var person = helloPort.findPersonById("P-001");
        System.out.println(
                "Personne trouvée : " +
                        person.getName() +
                        " (âge : " + person.getAge() + ")"
        );
    }
}
