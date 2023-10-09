package Mitosis;

import java.util.ArrayList;
import java.util.List;

public class Chromosome {
    private List<Chromatid> chromatids = new ArrayList<>();
    private int id;
    public Chromosome(Chromatid chromatid, int id) {
        chromatids.add(chromatid);
        this.id = id;
    }
    public Chromosome(Chromatid chromatid1, Chromatid chromatid2, int id) {
        chromatids.add(chromatid1);
        chromatids.add(chromatid2);
        this.id = id;
    }
    public void addChromatid(Chromatid chromatid) {
        if(chromatids.size() < 2) {
            chromatids.add(chromatid);
        } else {
            throw new IllegalStateException("Chromosome already has two chromatids!");
        }
    }
    public int numberOfChromatids() {
        return chromatids.size();
    }

    public Chromatid getChromatid(int index) {
        if (index < 0 || index >= chromatids.size()) {
            throw new IndexOutOfBoundsException("Chromatid index " + index + " is out of bounds for chromosome " + this.id + " with " + chromatids.size() + " chromatids.");
        }
        return this.chromatids.get(index);
    }

}
