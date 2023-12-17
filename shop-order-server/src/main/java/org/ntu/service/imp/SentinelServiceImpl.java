package org.ntu.service.imp;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class SentinelServiceImpl {
    @SentinelResource(value = "tranceService")
    public String resourceMethod() {
        return "业务资源";
    }
}
