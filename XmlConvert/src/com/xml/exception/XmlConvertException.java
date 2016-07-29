package com.xml.exception;

public class XmlConvertException extends RuntimeException{

	private static final long serialVersionUID = -4018013638258021253L;
	
	public XmlConvertException() {
		super();
	}
	
	public XmlConvertException(String message) {
		super(message);
	}
	
	public XmlConvertException(Throwable cause) {
		super(cause);
	}
	
	public XmlConvertException(String message, Throwable cause) {
		super(message, cause);
	}
}
