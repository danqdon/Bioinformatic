package Mitosis;

import Biomolecules.DNAStrand;

import java.util.List;

public class Cell {
    List<Chromosome> karyotpye;
    DNAPolymerase dnaPolymerase;
    public Cell(List<Chromosome> karyotype, DNAPolymerase dnaPolymerase){

        this.karyotpye = karyotype;
        this.dnaPolymerase = dnaPolymerase;
    }

    public List<Chromosome> getKaryotype() {
        return this.karyotpye;
    }

    public void replicateDNA() {
        for (Chromosome chromosome : this.karyotpye) {
            DNAStrand complementaryStrand = this.dnaPolymerase.getComplementary(chromosome.getChromatid(0).getDna());
            Chromatid complementaryChromatid = new Chromatid(complementaryStrand, chromosome.getChromatid(0).getId());
            chromosome.addChromatid(complementaryChromatid);
        }
    }
}
