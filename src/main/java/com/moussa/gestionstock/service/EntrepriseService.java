package com.moussa.gestionstock.service;

import com.moussa.gestionstock.dto.EntrepriseDto;


import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto entrepriseDto);

    EntrepriseDto findById(Integer id);

    List<EntrepriseDto> findAll();

    void delete(Integer id);
}
