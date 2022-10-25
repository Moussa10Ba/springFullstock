package com.moussa.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity {

    @Column(name = "designation")
    private String designation;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    @Column(name = "idEntreprise")
    private Integer idEntreprise;
}
