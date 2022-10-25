package com.moussa.gestionstock.service;

import com.moussa.gestionstock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    List<FournisseurDto> findAll();

    FournisseurDto findById(Integer id);

    void delete(Integer id);
}
