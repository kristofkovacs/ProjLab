
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class GameEngine {

    public Colonel oneill;
    public WormHole wormhole;
    public MapElement firstElement;

    public GameEngine() {
        System.out.println("Létrejön egy játékkezelő példánya.");
    }

    public GameEngine(Colonel _oneill, WormHole _wh){
        oneill = _oneill;
        wormhole = _wh;
    }


    public void startGame() {

        System.out.println("Udvozlunk a LiskovTheorem Oneill's Adventure nevu jatekaban!");
        System.out.println("Kerlek valassz egy tesztesetet!");
        System.out.println();
        System.out.println("0: Oneill ralep egy talajelemre");
        System.out.println("1: Oneill lelep egy merlegrol");
        System.out.println("2: Oneill nekimegy egy nyitott ajtonak");
        System.out.println("3: Oneill szakadekba lep");
        System.out.println("4: Oneill ralep egy merlegre");
        System.out.println("5: Oneill falnak megy");
        System.out.println("6: Oneill felvesz egy dobozt a talajrol");
        System.out.println("7: Oneill felvesz egy dobozt a merlegrol");
        System.out.println("8: Oneill letesz egy dobozt a talajra");
        System.out.println("9: Oneill letesz egy dobozt a merlegre");
        System.out.println("10: Oneill beletesz egy dobozt a szakadekba");
        System.out.println("11: Oneill specialis falra lo");
        System.out.println("12: Oneill belemegy a fereglyukba");
        System.out.println();
        System.out.println("Valasztott teszteset: ");

        Scanner s = new Scanner(System.in);
        String i = s.nextLine();

        try {
            switch (i) {
                //movement on ground
                case "0":
                    loadMap(0);
                    oneill.move(Direction.Up);
                    break;
                //movement from scale
                case "1":
                    oneill.move(Direction.Up);
                    break;
                //movement to door
                case "2":
                    oneill.move(Direction.Up);
                    break;
                //movement to rift
                case "3":
                    oneill.move(Direction.Up);
                    break;
                //movement to scale
                case "4":
                    oneill.move(Direction.Up);
                    break;
                //movement to wall
                case "5":
                    oneill.move(Direction.Up);
                    break;
                //pick up box from ground
                case "6":
                    oneill.pickUp();
                    break;
                //pick up box from scale
                case "7":
                    oneill.pickUp();
                    break;
                //put down box to ground
                case "8":
                    oneill.putDown();
                    break;
                //put down box to scale
                case "9":
                    oneill.putDown();
                    break;
                //put down box to rift
                case "10":
                    oneill.putDown();
                    break;
                //shoot bullet
                case "11":
                    oneill.shoot(Color.Yellow);
                    break;
                //walk into wormhole
                case "12":
                    oneill.move(Direction.Up);
                    break;

            }
        } catch (IOException e) {
            System.err.println("Hiba");
        }

    }


    public void loadMap(int testNumber) throws IOException {
        FileReader fr = new FileReader(Integer.toString(testNumber) + ".txt");
        BufferedReader br = new BufferedReader(fr);

        int row = Integer.parseInt(br.readLine());
        int column = Integer.parseInt(br.readLine());
        int thisLine = 0;
        MapElement table[][] = new MapElement[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                thisLine = Integer.parseInt(br.readLine());
                switch(thisLine){
                    case 1:
                        table[i][j] = new Ground(null, null);
                        break;
                    case 2:
                        ZPM z = new ZPM();
                        table[i][j] = new Ground(null, z);
                        break;
                    case 3:
                        Box b = new Box();
                        table[i][j] = new Ground(b, null);
                        break;
                    case 4:
                        Door d1 = new Door();
                        d1.open();
                        table[i][j] = d1;
                        break;
                    case 5:
                        table[i][j] = new Rift();
                        break;
                    case 6:
                        table[i][j] = new Wall();
                        break;
                    case 7:
                        table[i][j] = new SpecialWall(wormhole);
                        break;
                    case 8:
                        table[i][j] = new Ground(null, null);
                        table[i][j].setColonel(oneill);
                        break;
                    case 501:
                        Door d2 = new Door();
                        d2.close();
                        table[i][j] = d2;
                        break;
                    case 502:
                        table[i][j] = new Scale();
                        table[i][j].setColonel(oneill);
                        break;
                    case 503:
                        Scale s = new Scale();
                        s.createBox(new Box());
                        table[i][j] = new Scale();
                        break;
                    case 504:
                        SpecialWall sw1 = new SpecialWall(wormhole);
                        wormhole.setBPortal(Direction.Down, sw1);
                        sw1.setBPortal(wormhole.getBPortal());
                        table[i][j] = sw1;
                        break;
                    case 505:
                        SpecialWall sw2 = new SpecialWall(wormhole);
                        wormhole.setBPortal(Direction.Down, sw2);
                        sw2.setBPortal(wormhole.getBPortal());
                        table[i][j] = sw2;
                        break;
                    case 506:
                        Ground g = new Ground(null, null);
                        oneill.createBox(new Box());
                        g.setColonel(oneill);
                        table[i][j] = g;
                        break;
                    default:
                        break;
                }
            }
        }
        br.close();
        setNeighbours();
    }


    public void setNeighbours() {
        // TODO implement here
    }

}