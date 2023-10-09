package Mitosis;

import java.util.ArrayList;
import java.util.List;

public class Microtubule {
    private final DNAPolymerase dnaPolymerase;

    public Microtubule(DNAPolymerase dnaPolymerase) {
        this.dnaPolymerase = dnaPolymerase;
    }

    public List<Cell> divideCell(Cell cell) {
        List<Chromosome> originalChromosomes = cell.getKaryotpye();
        List<Chromatid> chromatidsForCell1 = new ArrayList<>();
        List<Chromatid> chromatidsForCell2 = new ArrayList<>();


        for (Chromosome chromosome : originalChromosomes) {
            chromatidsForCell1.add(chromosome.getChromatid(0));
            chromatidsForCell2.add(chromosome.getChromatid(1));
        }

        Cell daughterCell1 = new Cell(chromatidsToChromosomes(chromatidsForCell1), this.dnaPolymerase);
        Cell daughterCell2 = new Cell(chromatidsToChromosomes(chromatidsForCell2), this.dnaPolymerase);

        List<Cell> daughterCells = new ArrayList<>();
        daughterCells.add(daughterCell1);
        daughterCells.add(daughterCell2);

        return daughterCells;
    }

    private List<Chromosome> chromatidsToChromosomes(List<Chromatid> chromatids) {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (Chromatid chromatid : chromatids) {
            chromosomes.add(new Chromosome(chromatid, null, chromatid.getId())); // Assuming the Chromosome constructor can handle a null chromatid
        }
        return chromosomes;
    }
}
