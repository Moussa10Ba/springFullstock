package com.moussa.gestionstock.dto;
import com.moussa.gestionstock.model.Client;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String photo;

    private AdresseDto adresse;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    public static ClientDto fromEntity(Client client){
        if (client == null){
            return  null;
        }
     return  ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .mail(client.getMail())
                .photo(client.getPhoto())
                .numTel(client.getNumTel())
                .idEntreprise(client.getIdEntreprise())
                .build();
    }

    public  static Client toEntity(ClientDto clientDto){
            if (clientDto == null){
                return null;
            }
            Client client = new Client();
            client.setId(clientDto.getId());
            client.setNom(clientDto.getNom());
            client.setPrenom(clientDto.getPrenom());
            client.setMail(clientDto.getMail());
            client.setNumTel(clientDto.getNumTel());
            client.setPhoto(clientDto.getPhoto());
            client.setIdEntreprise(clientDto.getIdEntreprise());
            client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
            return client;
    }
}
