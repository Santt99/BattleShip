/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;


public class Boats {

    private int defense;
    private int spaces;
    private int attack;

    public Boats(int defense, int spaces, int attack) {
        this.attack = attack;
        this.defense = defense;
        this.spaces = spaces;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpaces() {
        return spaces;
    }

    public int getAttack() {
        return attack;
    }
}

