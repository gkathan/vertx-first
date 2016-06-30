package io.vertx.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.json.JsonObject;

public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut){

        Verticle second = new SecondVerticle();

        JsonObject config = new JsonObject().put("name","cactus").put("directory","caxit");

        DeploymentOptions options = new DeploymentOptions().setInstances(1);

        options.setConfig(config);



        vertx.deployVerticle("io.vertx.helloworld.SecondVerticle",options,res->{
            System.out.println("xxxx");
            if (res.succeeded()){
                System.out.println("deployment id: "+res.result()+" config: "+config.getString("name"));

            }
            else{
                System.out.println("deployment failed !");
            }
        });

        vertx.createHttpServer().requestHandler(r-> {
            r.response().end("<h1> hello world from caxit - this is my first verticle application - current counter: "+((SecondVerticle)second).getCounter()+"</h1>");
        }).listen(8081,result-> {
            if (result.succeeded()){
                fut.complete();
            }
            else{
                fut.fail(result.cause());
            }
        });
    }

}