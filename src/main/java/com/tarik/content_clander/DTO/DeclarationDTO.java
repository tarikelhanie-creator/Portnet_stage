package com.tarik.content_clander.DTO;

import java.time.LocalDate;

public class DeclarationDTO {
    private Long id_D;
    private String number;
    private String type;
    private String status;
    private LocalDate date_C;

    public DeclarationDTO (Long id_D, String number, String type, String status, LocalDate date){
        this.id_D=id_D;
        this.number =number;
        this.type =type;
        this.status =status;
        this.date_C = date;
    }

    public Long getId_D(){return id_D;}
    public String getNumber(){return number;}
    public String getType(){return type;}
    public String getStatus(){return status;}
    public LocalDate getDate_C() {return date_C;}
}
