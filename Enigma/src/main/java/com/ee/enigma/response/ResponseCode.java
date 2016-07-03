package com.ee.enigma.response;

public class ResponseCode {
	private int code;
	private String message;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
	 private Object resultObject;
	  
	
	public Object getResultObject()
  {
    return resultObject;
  }
  public void setResultObject(Object resultObject)
  {
    this.resultObject = resultObject;
  }
  @Override
	public String toString() {
		return "code: "+this.getCode()+", messages: "+this.getMessage();
	}
	
}
