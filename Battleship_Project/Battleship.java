/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;


public class Battleship {

    public static void main(String[] args) throws InterruptedException {
        Boats patrol = new Boats(1, 2, 1);
        Boats destroyer = new Boats(2, 3, 2);
        Boats carrier = new Boats(4, 5, 4);
        int life1 = 0, life2 = 0;
        boolean selectPlayer = true;
        mainMenu("    PLAY: press1 enter to play    ", "    QUIT: Press esc to quit    ");
        Map map = new Map(8, 11, 4);
        map.fillMapWithEmpty(map, 1);
        map.fillMapWithEmpty(map, 2);
        map.fillMapWithEmpty(map, 3);
        map.fillMapWithEmpty(map, 4);
        //fill the array with player boats
        while (life1 < 24 && life2 < 2) {
            map.printMap(map, 1);
            map.fillEachPlayerBoats(map, 1, patrol, "patrol = 1 deffernse point per space");
            map.printMap(map, 1);
            map.fillEachPlayerBoats(map, 1, destroyer, "destoryer = 2 deffernse point per space");
            map.printMap(map, 1);
            map.fillEachPlayerBoats(map, 1, carrier, "carrier = 4 deffernse point per space");
            map.printMap(map,1);
            Thread.sleep(2000);
            for(int i=0; i<50; i++){
                System.out.println("");
            }
            map.printMap(map, 2);
            map.fillEachPlayerBoats(map, 2, patrol, "patrol = 1 deffernse point per space");
            map.printMap(map, 2);
            map.fillEachPlayerBoats(map, 2, destroyer, "destoryer = 2 deffernse point per space");
            map.printMap(map, 2);
            map.fillEachPlayerBoats(map, 2, carrier, "carrier = 4 deffernse point per space");
            life1 = map.consultingLife(map, life1, 1);
            life2 = map.consultingLife(map, life2, 2);
            Thread.sleep(2000);
            for(int i=0; i<50; i++){
                System.out.println("");
            }
        }
        //the game mechanic 
        do {
            //player 1
            life1 = map.consultingLife(map, life1, 1);
            if (selectPlayer && life2 > 0 && life1 > 0) {
                map.printMap(map, 3);
                map.attack(map, patrol, destroyer, carrier, 2);
                selectPlayer = false;
                Thread.sleep(2000);
                for(int i=0; i<50; i++){
                    System.out.println("");
                }
            }
            //player2
            life2 = map.consultingLife(map, life2, 2);
            if (!selectPlayer && life1 > 0 && life2 > 0) {
                map.printMap(map, 4);
                map.attack(map, patrol, destroyer, carrier, 1);
                selectPlayer = true;
                Thread.sleep(2000);
                for(int i=0; i<50; i++){
                    System.out.println("");
                }
            }
        } while (life1 > 0 && life2 > 0);
        if (life1 > 0) {
            System.out.println("Win Player 1");
        } else {
            System.out.println("Win player 2");
        }
    }

    //menu
    public static void mainMenu(String play, String quit) {
        String mainMenu[] = new String[2];
        mainMenu[0] = play;
        mainMenu[1] = quit;
        for (String mainMenu1 : mainMenu) {
            System.out.println("  ");
            System.out.println(mainMenu1);
            System.out.println("  ");
        }
    }
}
