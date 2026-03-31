package com.example.cliente;
import java.time.LocalDate;

import com.example.cliente.Usuario;

public class Usuario {
    private String nome;
    private String telefone;
    private LocalDate data;
    private double valor;
    private String local;
    private String email;
    

    public Usuario(String nome, String telefone, LocalDate data, double valor, String local, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.data = data;
        this.valor = valor;
        this.local = local;
        this.email = email;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public String getLocal(){
        return local;
    }

    public void setLocal(String local){
        this.local = local;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
