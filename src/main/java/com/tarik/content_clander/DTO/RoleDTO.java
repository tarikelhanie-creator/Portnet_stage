package com.tarik.content_clander.DTO;

public class RoleDTO {
    private Long id_R;
    private String name;
    private String desc;

    public RoleDTO(Long id, String n, String d){
        this.id_R = id;
        this.name =n;
        this.desc =d;
    }

    public Long getId_R(){return id_R;}
    public String getName(){return name;}
    public String getDesc(){return desc;}
}
