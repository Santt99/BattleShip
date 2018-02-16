/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Scanner;

/**
 *
 * @author felip
 */
public class Map {

    private int x, y, z;
    public Boats map[][][];

    //Constructor of Map
    public Map(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.map = new Boats[x][y][z];
    }

    //Fill the array with empty boats 
    public void fillMapWithEmpty(Map map, int user) {
        for (int i = 0; i < 11; i++) {

            for (int j = 0; j < 8; j++) {
                map.map[j][i][user - 1] = new Empty(0, 1, 0);

            }
        }
    }

    //Contult boat.getDefense(); to know if a player have die;
    public int consultingLife(Map map, int life, int user) {
        for (int i = 0; i < 11; i++) {

            for (int j = 0; j < 8; j++) {
                life += map.map[j][i][user - 1].getDefense();

            }
        }
        return life;
    }

    //Print the array
    public void printMap(Map map, int user) {
        System.out.println("------------------------ User:" + user + " ------------------------");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(" | " + map.map[j][i][user - 1].getDefense() + " | ");
            }
            System.out.println("");
        }
        System.out.println("---------------------------------------------------------");
    }

    //Fill the map with the player boats
    public void fillEachPlayerBoats(Map map, int user, Boats boat, String type) {
        int pos1, pos2 = 0, choice, column, row, spaces;
        boolean bla = true;
        spaces = boat.getSpaces();
        Scanner scan = new Scanner(System.in);
        System.out.println(type);
        System.out.println("Write the number to select on what direction you want to put your boats\n1)Vertical\n2)Horizontal");
        choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Write the number of column(Enter Column Number)");
                column = scan.nextInt();
                while (bla) {
                    System.out.println("Enter a coordenate(Enter a Number 1-11)");
                    pos1 = scan.nextInt();
                    if (pos1 > 0 && pos1 < 12 && map.map[column - 1][pos1 - 1][user - 1].getDefense() == 0) {
                        map.map[column - 1][pos1 - 1][user - 1] = boat;
                        pos2 = pos1;
                        spaces = spaces - 1;
                        bla = false;
                    } else {
                        System.err.println("Enter a valid coordenate");
                    }
                }
                while (spaces > 0) {
                    System.out.println("Enter a coordenate(Enter a Number 1-11)");
                    pos1 = scan.nextInt();
                    if (pos1 - pos2 == 1 && map.map[column - 1][pos1 - 1][user - 1].getDefense() == 0 || pos1 - pos2 == -1 && map.map[column - 1][pos1 - 1][user - 1].getDefense() == 0) {
                        map.map[column - 1][pos1 - 1][user - 1] = boat;
                        spaces = spaces - 1;
                        pos2 = pos1;
                    } else {
                        System.err.println("Enter a valid coordenate");
                    }
                }
                break;
            case 2:
                System.out.println("Write the number of row(Enter Row Number)");
                row = scan.nextInt();
                while (bla) {
                    System.out.println("Enter a coordenate(Enter a Number 1-8)");
                    pos1 = scan.nextInt();
                    if (pos1 > 0 && pos1 < 12 && map.map[pos1 - 1][row - 1][user - 1].getDefense() == 0) {
                        map.map[pos1 - 1][row - 1][user - 1] = boat;
                        pos2 = pos1;
                        spaces = spaces - 1;
                        bla = false;
                    } else {
                        System.err.println("Enter a valid coordenate");
                    }
                }
                while (spaces > 0) {
                    System.out.println("Enter a row(Enter a Number 1-11)");
                    pos1 = scan.nextInt();
                    if (pos1 - pos2 == 1 && map.map[pos1 - 1][row - 1][user - 1].getDefense() == 0 || pos1 - pos2 == -1 && map.map[pos1 - 1][row - 1][user - 1].getDefense() == 0) {
                        map.map[pos1 - 1][row - 1][user - 1] = boat;
                        spaces = spaces - 1;
                        pos2 = pos1;
                    } else {
                        System.err.println("Enter a valid coordenate");
                    }
                }
                break;
            default:
                System.out.println("Enter a valid option");
                break;
        }

    }

    //Here is where the atack hapend
    public void attack(Map map, Boats boat, Boats boat1, Boats boat2, int user) {
        Scanner scan = new Scanner(System.in);
        int choice, x = 0, y = 0;
        System.out.println("Select the boat you want to choose to attack with\n1. Patrol\n2. Destroyer\n3. Carrier");
        choice = scan.nextInt();
        if (choice == 1) {
            validateCoordenate(x, y, map, boat, user, scan);
        } else if (choice == 2) {
            validateCoordenate(x, y, map, boat1, user, scan);
        } else if (choice == 3) {
            validateCoordenate(x, y, map, boat2, user, scan);
        } else {
            System.out.println("Select a Valid boat");
        }

    }

    //Calculate if the place you shoot exist o have a boat, and make the atack happend
    public void validateCoordenate(int x, int y, Map map, Boats boat, int user, Scanner scan) {
        System.out.println("Write x coordenate you what to atack");
        x = scan.nextInt();
        System.out.println("Write y coordenate you what to atack");
        y = scan.nextInt();
        if (map.map[x][y][user - 1].getDefense() > 0) {
            System.err.println("You shoot to: " + map.map[x][y][user - 1].getClass());
            map.map[x][y][user - 1].setDefense(map.map[x][y][user - 1].getDefense() - boat.getAttack());
        } else {
            System.err.println("You failed your shoot");
            map.map[x][y][user + 1].setDefense(-1);
        }
    }
}
