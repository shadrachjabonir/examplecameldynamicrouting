package com.sjabonir;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    static List<String> listRoutes;

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        listRoutes = new ArrayList<>();
        listRoutes.add("route1");
        listRoutes.add("route2");
        listRoutes.add("route3");
        listRoutes.add("route4");
        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
        from("file:src/data?noop=true")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(listRoutes);
                    }
                })
                .log("${body}")
                .split(body()).dynamicRouter(method(DynamicRouterBean.class, "route"))
                .log("${body}");

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

//            .choice()
//                .when(xpath("/person/city = 'London'"))
//                    .log("UK message")
//                    .to("file:target/messages/uk")
//                .otherwise()
//                    .log("Other message")
//                    .to("file:target/messages/others");
    }

}
