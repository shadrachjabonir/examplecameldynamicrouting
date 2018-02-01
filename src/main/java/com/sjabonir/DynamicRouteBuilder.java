package com.sjabonir;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.annotation.PostConstruct;
import java.util.List;

public class DynamicRouteBuilder extends RouteBuilder {

    static List<String> listRoutes;

    @PostConstruct
    void init(){
        listRoutes.add("route1");
        listRoutes.add("route2");
        listRoutes.add("route3");
        listRoutes.add("route4");
    }

    @Override
    public void configure() throws Exception {
        System.out.println("haiiii");
        from("direct:start")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(listRoutes);
                    }
                })
                .split(body()).dynamicRouter(method(DynamicRouterBean.class, "route"));

        from("direct:route1").process(new Processor() {
            public void process(Exchange exchange) {
                String body = exchange.getIn().getBody().toString();
                body = body + " in route 1";
                System.out.println(body);
                exchange.getIn().setBody(body);
            }
        });

        from("direct:route2").process(new Processor() {
            public void process(Exchange exchange) {
                String body = exchange.getIn().getBody().toString();
                body = body + " in route 2";
                System.out.println(body);
                exchange.getIn().setBody(body);
            }
        });

        from("direct:route3").process(new Processor() {
            public void process(Exchange exchange) {
                String body = exchange.getIn().getBody().toString();
                body = body + " in route 3";
                System.out.println(body);
                exchange.getIn().setBody(body);
            }
        });

        from("direct:route4").process(new Processor() {
            public void process(Exchange exchange) {
                String body = exchange.getIn().getBody().toString();
                body = body + " in route 4";
                System.out.println(body);
                exchange.getIn().setBody(body);
            }
        });
    }
}
