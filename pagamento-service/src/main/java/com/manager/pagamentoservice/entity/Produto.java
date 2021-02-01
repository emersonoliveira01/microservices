package com.manager.pagamentoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    private Long id;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;
}
