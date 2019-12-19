package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaBean implements Serializable {

    private int n = 1;
    private List<Dot> Dots;
    private Boolean lastHit = null;

    public JavaBean() {
        Dots = new ArrayList<>();
    }

    void addDot(Dot dot) {
        n++;
        Dots.add(dot);
        lastHit = dot.isHit();
    }

    public List getDots() {
        while (Dots.size() > 10) {
            Dots.remove(0);
        }

        List<Dot> reversed = new ArrayList(Dots);
        Collections.reverse(reversed);

        return reversed;
    }

    public Boolean getLastHit(){
        return lastHit;
    }

}