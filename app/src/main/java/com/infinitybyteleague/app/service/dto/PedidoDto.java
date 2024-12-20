package com.infinitybyteleague.app.service.dto;

import java.time.LocalDateTime;

public class PedidoDto {
    private Long idUser; // id que me permite asocial la Order al User
    private LocalDateTime date;
    private Double total;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
