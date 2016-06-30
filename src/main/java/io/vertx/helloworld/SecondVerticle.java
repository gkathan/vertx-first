package io.vertx.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

/**
 * Created by gkathan on 2016-06-30.
 */
public class SecondVerticle extends AbstractVerticle {

    public int getCounter() {
        return counter;
    }

    private int counter;

    @Override
    public void start(Future<Void> fut){
        vertx.setPeriodic(1000,id->{
            System.out.println("hello verticle console :-) "+counter);
            counter++;
        });

    }

    @Override
    public void stop(){
        System.out.println("stopped my verticle..");
    }
}
