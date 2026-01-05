package org.example.impl;

import org.example.api.HelloService;
import org.example.model.Person;
import jakarta.jws.WebService;

@WebService(
        serviceName = "HelloService",
        portName = "HelloServicePort",
        endpointInterface = "org.example.api.HelloService",
        targetNamespace = "http://api.cxf.acme.com/"
)
public class GreetingServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        String value = (name == null || name.isBlank()) ? "inconnu" : name;
        return "Bonjour, " + value;
    }

    @Override
    public Person findPersonById(String id) {
        // Implémentation fictive (maquette)
        return new Person(id, "Ada Lovelace", 36);
    }
}
