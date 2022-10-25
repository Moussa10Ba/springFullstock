package com.moussa.gestionstock.dto;

import com.moussa.gestionstock.model.LigneVente;
import com.moussa.gestionstock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVentes;

    public static VentesDto fromEntity(Ventes vente){
        if (vente == null){
            return null;
        }
       return VentesDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .idEntreprise(vente.getIdEntreprise())
                .build();
    }

    public static Ventes toEntity(VentesDto ventesDto){
        if (ventesDto == null){
            return null;
        }
        Ventes vente = new Ventes();
        vente.setId(ventesDto.getId());
        vente.setCode(ventesDto.getCode());
        vente.setDateVente(ventesDto.getDateVente());
        vente.setIdEntreprise(ventesDto.getIdEntreprise());
        return vente;
    }


}
