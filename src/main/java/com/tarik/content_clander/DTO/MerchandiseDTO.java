package com.tarik.content_clander.DTO;

public class MerchandiseDTO {
    private Long id_M;
    private String designation;
    private float weight;
    private float value;
    private int quantite;

    public MerchandiseDTO(Long id, String d, float p, float v, int q){
        this.id_M =id;
        this.designation = d;
        this.weight = p;
        this.value =v;
        this.quantite =q;
    }

    public Long getId_M(){return id_M;}
    public String getDesignation(){return designation;}
    public float getWeight(){return weight;}
    public float getValue(){return value;}
    public int getQuantite(){return quantite;}
}
