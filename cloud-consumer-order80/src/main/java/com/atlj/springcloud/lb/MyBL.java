package com.atlj.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyBL implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> instances) {

        int index = getAndIncrement() % instances.size();
        return instances.get(index);
    }

    public final int getAndIncrement(){
        for (;; ) {
            int current = atomicInteger.get();
            int next = current > 2147483647 ? 0 : current+1;
            if (atomicInteger.compareAndSet(current,next)){
                System.out.println("****next: 次数为" +next);
                return next;
            }
        }
    }
}
