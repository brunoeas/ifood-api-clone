package com.brunosoares.ifood.pedido.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.brunosoares.ifood.pedido.dto.PedidoRealizadoDTO;
import io.quarkus.runtime.StartupEvent;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;

@ApplicationScoped
public class ElasticSearchService {

    private ElasticsearchClient client;

    void startUp(@Observes StartupEvent startupEvent) {
        final RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();

        // Create the transport with a Jackson mapper
        final ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);

        Logger.getAnonymousLogger().info("ElasticSearch no ar");
    }

    public void index(final String index, final PedidoRealizadoDTO dto) {
        final IndexRequest<PedidoRealizadoDTO> indexRequest = new IndexRequest.Builder<PedidoRealizadoDTO>()
                .index(index)
                .document(dto)
                .build();
        try {
            this.client.index(indexRequest);
        } catch (final IOException e) {
            Logger.getAnonymousLogger().log(Level.ERROR, "Erro no index do ElasticSearch", e);
        }
    }

}
