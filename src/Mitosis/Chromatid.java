package Mitosis;

import Biomolecules.DNAStrand;

public class Chromatid {
    private final DNAStrand dnaStrand;
    private final int id;

    public Chromatid(DNAStrand dnaStrand, int id) {
        this.dnaStrand = dnaStrand;
        this.id = id;
    }
    public DNAStrand getDna() {
        return this.dnaStrand;
    }

    public int getId() {
        return this.id;
    }


}
