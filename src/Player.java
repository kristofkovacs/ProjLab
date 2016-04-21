
import java.util.*;

/**
 * Az ezredes osztaly
 */
public class Player {

    private Direction lookDirection;
    private Box box;
    private int ZPMNumber;
    private MapElement baseElement;
    private boolean isAlive;

    //Az ezredes inicializalasa, attributumainak alapertelmezett ertekekre allitasa
    public Player() {
        lookDirection = Direction.Up;
        ZPMNumber = 0;
        box = null;
        isAlive = true;
        System.out.println("Letrejon ezredes egy peldanya.");
    }

    public Player(MapElement _baseElement){
        lookDirection = Direction.Up;
        baseElement = _baseElement;
        ZPMNumber = 0;
        box = null;
        isAlive = true;
    }

    //Visszater azzal az irannyal, ahova az ezredes nez
    public Direction getLookDirection(){
        return lookDirection;
    }

    //Beallitja az ezredes nezopontjat
    public void setLookDirection(Direction dir){
        lookDirection = dir;
    }

    //Az ezredes mozgatasa a parameterben kapott irany szerint
    public void move(Direction dir) {
        setLookDirection(dir); // a kapott haladasi irany alapjan valtoztatja meg az ezredes iranyat
        MapElement nextElement = baseElement.getNextElement(dir); //lekeri az iranynak megfelelo kovetkezo elemet

        if(nextElement.stepOn(this)) { //ha a kovetkezo elemre ra lehet lepni
            if (nextElement.getClass() == SpecialWall.class){
                SpecialWall sw = (SpecialWall)nextElement;
                nextElement = sw.walkthroughWormHole(this);
                if (nextElement.stepOn(this)) {
                    stepOff(nextElement); // akkor lepjen le a jelenlegi elemrol
                    System.out.println("Az ezredes sikeresen ralepett a kivant mezore");
                }
            }
            else {
                stepOff(nextElement); // akkor lepjen le a jelenlegi elemrol
                System.out.println("Az ezredes sikeresen ralepett a kivant mezore");
            }

        }
    }

    //Loves az adott szinu golyoval
    public void shoot(Color color) {

        Bullet b = new Bullet(lookDirection, baseElement, color);

        b.move();

    }

    //Az ezredes ongyilkossaga
    public void kill() {
        isAlive = false;
        System.out.println("Az ezredes ongyilkos lett");
    }

    //Doboz felvetele
    public void pickUp() {
        System.out.println("Az ezredes fel szeretne venni a dobozt");
        MapElement nextElement = baseElement.getNextElement(lookDirection);//lekeri az iranynak megfelelo kovetkezo elemet
        box = nextElement.pickUp();
    }


    public void turn() {
        // TODO implement here
    }


    //Doboz letevese
    public void putDown() {
        System.out.println("Az ezredes le szeretne tenni a dobozt");
        MapElement nextElement = baseElement.getNextElement(lookDirection);//lekeri az iranynak megfelelo kovetkezo elemet
        if(box != null) {
            System.out.println("Az ezredesnel van doboz");
            if (nextElement.putDown(box)) { //ha a kovetkezo elemre leteheto a doboz,
                removeBox();                //akkor leteszi.
                System.out.println("A doboz le lett teve");
            }
        } else {
            System.out.println("Az ezredesnel nincs doboz");
        }
    }


    //ZPM szam novelese
    public void incZPMNumber() {
        ZPMNumber++;
        System.out.println("Nott a ZPM szam");
    }


    //Csak a tesztesetek latvanyos feltuntetese celjabol
    public void createBox(Box b) {
        box = b;
        System.out.println("Az ezredesnel van doboz");
    }

    //Doboz referenciajanak torlese
    public void removeBox() {
        box = null;
        System.out.println("A doboz le lett teve");
    }


    //A kovetekezo elemnek odaadja a sajat referenciajat, ezzel ralepteti es a mostanirol leveszi. Ezzel hajtodik vegre a lepes
    public void stepOff(MapElement me) {
        me.setPlayer(this);
        baseElement.setPlayer(null);
        baseElement = me;
        System.out.println("Az ezredes lelepett az aktualis elemrol");
        if(me.isSpecWall) {

        }
        else if(me.isScale){

        }
        else {
            me.setPlayer(this);
            baseElement.setPlayer(null);
            baseElement = me;
        }
        System.out.println("Az ezredes a kovetkezo elemre lepett");
    }

    public void setBaseElement(MapElement me) {
        baseElement = me;
    }

}