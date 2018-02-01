package com.sjabonir;

import org.apache.camel.Exchange;
import org.apache.camel.Header;

public class DynamicRouterBean {
    public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previousRoute) {
        if (body.toString().equals("route1")) {
            return "direct://route1";
        } else if (body.toString().equals("route2")) {
            return "direct://route2";
        } else if (body.toString().equals("route3")) {
            return "direct://route3";
        } else if (body.toString().equals("route4")) {
            return "direct://route4";
        } else {
            return null;
        }
    }
}
