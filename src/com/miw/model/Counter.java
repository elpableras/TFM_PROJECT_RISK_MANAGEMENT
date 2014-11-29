package com.miw.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

/**
 * Modelo de datos para la información sobre la conexión
 * 
 * @author Pablo
 * 
 */
public class Counter {
    private Integer value = 0;
    private String ip = "";
    private String timestamp = "";

    public Integer getValue() {
	return value;
    }

    public void increment() {
	value++;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public String getIp() {
	return ip;
    }

    public String getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
    }

    public void ip() {
	InetAddress ia;
	try {
	    ia = InetAddress.getLocalHost();
	    setIp(ia.getHostAddress());
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
    }

    public void timestamp() {
	java.util.Date date = new java.util.Date();
	setTimestamp(String.valueOf(new Timestamp(date.getTime())));
    }

    @Override
    public String toString() {
	return value.toString();
    }
}
