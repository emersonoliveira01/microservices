package com.manager.pagamentoservice.listener;

import com.manager.pagamentoservice.entity.Produto;
import com.manager.pagamentoservice.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReciveMessenge {

    @Autowired
    private ProdutoRepository produtoRepository;

    @RabbitListener(queues = "${crud.rabbitmq.queue}")
    public void receive(@Payload Produto produto) {
        produtoRepository.save(produto);
    }
}
