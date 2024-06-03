package br.com.diogotb.apirest_senai.Model;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.*;

import lombok.*;

@Entity
@Setter
@Getter
public class Ambiente implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Responsavel responsavel;

    
}