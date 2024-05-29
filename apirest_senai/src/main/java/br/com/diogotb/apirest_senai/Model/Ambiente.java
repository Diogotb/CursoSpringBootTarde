package br.com.diogotb.apirest_senai.Model;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;

@Entity
@Setter
@Getter
public class Ambiente implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "ambiente")
    private List<AtivoPatrimonial> ativos;

    @OneToOne
    @JoinColumn(name = "responsavel_id",referencedColumnName = "id", nullable = false)
    private Responsavel responsavel;

    
}