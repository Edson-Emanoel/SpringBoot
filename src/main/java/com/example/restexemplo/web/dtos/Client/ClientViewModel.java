package com.example.restexemplo.web.dtos.Client;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.restexemplo.core.models.Client;
import com.example.restexemplo.core.utils.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientViewModel {
    
    private Long id;
    private String name;
    private String phone;
    private String email;

    public String getPhone(){
        return StringUtils.FormatPhone(phone);
    }

    public static ClientViewModel of(Client client){
        return ClientViewModel.builder()
            .id(client.getId())
            .name(client.getName())
            .email(client.getEmail())
            .phone(client.getPhone())
            .build();
    }
}
