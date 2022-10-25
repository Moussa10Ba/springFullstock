package com.moussa.gestionstock.service;

import com.moussa.gestionstock.dto.VentesDto;

import java.util.List;

public interface VenteService {

    VentesDto save (VentesDto ventesDto);

    VentesDto findById(Integer id);

    List<VentesDto> findAll();

    VentesDto findByCode(String code);

    void delete(Integer id);
}
