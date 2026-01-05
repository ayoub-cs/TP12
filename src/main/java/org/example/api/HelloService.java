package org.example.api;

import org.example.model.Person;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(targetNamespace = "http://api.cxf.acme.com/")
public interface GreetingService {

    @WebMethod(operationName = "SayHello")
    @WebResult(name = "greeting")
    String greet(@WebParam(name = "name") String name);

    @WebMethod(operationName = "FindPerson")
    @WebResult(name = "person")
    Person getPersonById(@WebParam(name = "id") String id);
}
