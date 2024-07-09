package com.example.restexemplo.web.dtos.Client;

import com.example.restexemplo.core.models.Client;
import com.example.restexemplo.core.utils.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientForm {
    
    private String name;
    private String email;
    private String phone;

    public String cleanedPhone(){ // todos caracteres diferent de 1 a 9
        return phone.replaceAll("[^0-9]", "");
    }

    public Client toClient(){
        return Client.builder()
            .name(name)
            .email(email)
            .phone(StringUtils.cleanPhone(phone))
            .build();
    }

    public static ClientForm of(Client client){
        return ClientForm.builder()
           .name(client.getName())
           .email(client.getEmail())
           .phone(StringUtils.FormatPhone(client.getPhone()))
           .build();
    }
}
