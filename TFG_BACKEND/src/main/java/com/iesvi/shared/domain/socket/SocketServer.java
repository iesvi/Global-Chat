package com.iesvi.shared.domain.socket;

import com.iesvi.shared.application.Dto;

public interface SocketServer {

    void Send(Integer clientId, String texto);

    void Send(Integer clientId, Dto object);

}
