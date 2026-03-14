package com.tarik.content_clander.DTO;

public class UserDTO {
    private Long id_U;
    private String user_name;
    private String email;

    public UserDTO (Long id,String user_name, String email){
        this.id_U =id;
        this.user_name=user_name;
        this.email =email;
    }
    public Long getId_U(){return id_U;}
    public String getUser_name(){return user_name;}
    public String getEmail(){return email;}

}
