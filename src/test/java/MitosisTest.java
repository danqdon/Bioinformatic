import Biomolecules.DNAStrand;
import CellCycle.Mitosis.*;
import Deserializers.TxtDNADeserializer;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MitosisTest {

    @Test
    public void name() {
        TxtDNADeserializer dnaDeserializer = new TxtDNADeserializer();
        DNAStrand dnaStrand = dnaDeserializer.deserialize("genome.txt");

        GenomeOrganizer organizer = new GenomeOrganizer();
        List<Chromatid> chromatidList = organizer.organizeIntoChromatids(dnaStrand);

        DNAPolymerase dnaPolymerase = new DNAPolymerase();
        List<Chromosome> chromosomes = new ArrayList<>();

        for (Chromatid chromatid : chromatidList) {
            DNAStrand complementaryStrand = dnaPolymerase.getComplementary(chromatid.getDna()); // Assuming Chromatid has a method getDNA()
            Chromatid complementaryChromatid = new Chromatid(complementaryStrand, chromatid.getId()); // Assuming IDs are the same for complementary chromatids
            chromosomes.add(new Chromosome(chromatid, complementaryChromatid, chromatid.getId()));
        }

        Cell cell = new Cell(chromosomes);
        Microtubule microtubule = new Microtubule();
        List<Cell> daughterCells = microtubule.divideCell(cell);
        Cell daughterCell1 = daughterCells.get(0);
        Cell daughterCell2 = daughterCells.get(1);

    }


}
