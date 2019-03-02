package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by marija on 4/27/16.
 */
public class RepeatExt {
    public Repeat commonBody;
    public HashMap<Integer, Repeat> endings = new HashMap<>();
    public RepeatExt() {
    }

    public void addCommonBody(int beg, int end) {
        commonBody = new Repeat(beg, end);
    }

    public void addEnding(int beg, int end) {
        Repeat ending = new Repeat(beg, end);
        this.endings.put(end, ending);
    }

    public int getUpperBound() {
        if (endings.isEmpty()) {
            return commonBody.end;
        }
        else {
            int max = Collections.max(endings.keySet());
            return endings.get(max).end;
        }
    }

    public int getLowerBound() {
        return commonBody.beg;
    }
}