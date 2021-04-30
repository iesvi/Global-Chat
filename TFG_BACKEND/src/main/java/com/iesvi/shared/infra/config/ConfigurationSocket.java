package com.iesvi.shared.infra.config;

import com.iesvi.shared.domain.socket.SocketServer;
import com.iesvi.shared.infra.socket.MultiThreadedServerTcp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class ConfigurationSocket {

    @Autowired
    private TaskExecutor taskExecutor;

    @Bean
    public SocketServer createSocketServer() {
        MultiThreadedServerTcp server = new MultiThreadedServerTcp(5555);
        taskExecutor.execute(server);

        return server;
    }

}
