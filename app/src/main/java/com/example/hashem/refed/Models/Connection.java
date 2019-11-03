package com.example.hashem.refed.Models;

public class Connection {

    String URL_IP = "192.168.1.5";

    @Override
    public String toString() {
        return "http://" + URL_IP;
    }

    public Connection(String URL_IP) {
        this.URL_IP = URL_IP;
    }

    public Connection() {
        this.URL_IP = URL_IP;
    }

    public void setURL_IP(String URL_IP) {
        this.URL_IP = URL_IP;
    }
}
