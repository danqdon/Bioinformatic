package Mitosis;

import java.util.ArrayList;
import java.util.List;

public class DNALigase {
    private final DNAPolymerase polymerase;

    public DNALigase(DNAPolymerase polymerase) {
        this.polymerase = polymerase;
    }

    public Cell replicate(Cell cell){
        List<KaryotypeElement> replicatedKaryotype = new ArrayList<>();
        for (KaryotypeElement karyotypeElement: cell.getKaryotype()){
            Chromosome chromosome = karyotypeElement.getChromosome(0);
            Chromatid chromatid1 =  chromosome.getChromatid(0);
            Chromatid chromatid2 = chromosome.getChromatid(1);
            Chromatid chromatid1Complementary = polymerase.getComplementary(chromatid1);
            Chromatid chromatid2Complementary = polymerase.getComplementary(chromatid2);
            Chromosome chromosome1 = new Chromosome(chromatid1,chromatid1Complementary, chromatid1.getId());
            Chromosome chromosome2 = new Chromosome(chromatid2Complementary, chromatid2, chromatid2.getId());
            replicatedKaryotype.add(new KaryotypeElement(chromosome1,chromosome2,chromosome1.getId()));
        }
        return new Cell(replicatedKaryotype, cell.getDnaPolymerase(), cell.getDnaLigase());
    }
}
