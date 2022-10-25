package com.moussa.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Article extends  AbstractEntity{

    @Column(name = "codearticle")
    private String codeAricle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixunitaireht;

    @Column(name = "prixunitairettc")
    private BigDecimal prixunitairettc;

    @Column(name = "tauxtva")
    private BigDecimal tauxtva;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

    @Column(name = "identreprise")
    private Integer idEntreprise;

   



}
