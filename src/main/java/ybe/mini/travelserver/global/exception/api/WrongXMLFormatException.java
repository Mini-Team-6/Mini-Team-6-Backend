package ybe.mini.travelserver.global.exception.api;

import jakarta.xml.bind.JAXBException;

public class WrongXMLFormatException extends RuntimeException {
    public WrongXMLFormatException(JAXBException e) {
        super(e);
    }
}
