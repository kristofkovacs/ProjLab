
import java.util.*;


public class Scale extends MapElement {

    private Box box;
    private Door door;

    public Scale() {
        this.box = null;
        System.out.println("Sikerült létrehozni egy mérleg objektumot.");
    }

    public boolean hasBox() {
        if (box != null) {
            System.out.println("A mérlegen van doboz.");
            return true;
        }
        System.out.println("A mérlegen nincs doboz.");
        return false;
    }

    public void setDoor(Door d) {
        door = d;
    }

    public void removeBox() {
        box = null;
        System.out.println("A mérlegen nincs többé doboz.");
    }

    public void createBox(Box box) {
        this.box = box;
        System.out.println("A mérlegre rákerült egy doboz");
    }


    public boolean stepOn(Colonel colonel) {
        if (hasBox()) {
            System.out.println("Az ezredes nem léphet rá a mérlegre, mert azon van doboz.");
            return false;
        }
        System.out.println("Az ezredes ráléphet a mérlegre.");
        return true;
    }


    public Box pickUp() {
        if (hasBox()) {
            System.out.println("Sikerült felvenni a dobozt a mérlegről.");
            return box;
        }
        System.out.println("A mérlegen nem volt doboz, így nem volt mit felvenni a mérlegről.");
        return null;
    }


    public boolean putDown(Box box) {
        if(!hasBox()) {
            this.box = box;
            System.out.println("Sikerült új doboz elemet letenni a mérlegre.");
            return true;
        } else {
            System.out.println("A mérlegen már van doboz, így nem sikerült új doboz elemet letenni rá.");
            return false;
        }
    }

}