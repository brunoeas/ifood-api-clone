package com.brunosoares.ifood.cadastro;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class Aplicacao {

//    @Incoming("requests")
    public void process(String body) {
        Logger.getAnonymousLogger().info("mensagem recebida: {}" + body);
    }

}
