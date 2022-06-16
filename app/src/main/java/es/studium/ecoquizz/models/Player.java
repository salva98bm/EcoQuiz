package es.studium.ecoquizz.models;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String name;
    private String password;
    private int score;
    private int icon;

    //Default
    public Player(){
        this.name = "";
        this.password = "";
        this.score = 0;
        this.icon = 0;
    }

    //Complete
    public Player(int id, String name, String clave, int puntuacion, int icono) {
        this.id = id;
        this.name = name;
        this.password = clave;
        this.score = puntuacion;
        this.icon = icono;
    }

    //Constructor sin el id ni el icono
    public Player(String nombre, String clave, int puntuacion) {
        this.name = nombre;
        this.password = clave;
        this.score = puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
