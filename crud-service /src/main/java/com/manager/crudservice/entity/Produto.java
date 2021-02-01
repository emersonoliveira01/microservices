package com.manager.crudservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome" ,nullable = false, length = 255)
    private String nome;

    @Column(name = "estoque" ,nullable = false, length = 10)
    private Integer estoque;

    @Column(name = "preco" ,nullable = false, length = 10)
    private Double preco;
}
