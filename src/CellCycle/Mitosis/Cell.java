package CellCycle.Mitosis;

import Biomolecules.DNAStrand;

import java.util.List;

public class Cell {
    List<Chromosome> karyotpye;
    public Cell(List<Chromosome> karyotype){
        this.karyotpye = karyotype;
    }

    public List<Chromosome> getKaryotpye() {
        return karyotpye;
    }

    public void replicateDNA() {
        DNAPolymerase dnaPolymerase = new DNAPolymerase();
        for (Chromosome chromosome : karyotpye) {
            DNAStrand complementaryStrand = dnaPolymerase.getComplementary(chromosome.getChromatid(1).getDna());
            Chromatid complementaryChromatid = new Chromatid(complementaryStrand, chromosome.getChromatid(2).getId());
            chromosome.addChromatid(complementaryChromatid);
        }
    }
}
