import Biomolecules.DNAStrand;
import CellCycle.Mitosis.*;
import Deserializers.TxtDNADeserializer;
import Mitosis.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MitosisTest {

    @Test
    public void shouldCreateTwoIdenticalDaughterCellsFromOne() {
        TxtDNADeserializer dnaDeserializer = new TxtDNADeserializer();
        DNAStrand dnaStrand = dnaDeserializer.deserialize("genome.txt");

        GenomeOrganizer organizer = new GenomeOrganizer();
        List<Chromatid> chromatidList = organizer.organizeIntoChromatids(dnaStrand);
        List<Chromosome> chromosomes = new ArrayList<>();
        for (Chromatid chromatid : chromatidList)
            chromosomes.add(new Chromosome(chromatid, chromatid.getId()));
        DNAPolymerase dnaPolymerase = new DNAPolymerase();
        Cell cell = new Cell(chromosomes, dnaPolymerase);
        cell.replicateDNA();
        Microtubule microtubule = new Microtubule(dnaPolymerase);
        List<Cell> daughterCells = microtubule.divideCell(cell);
        Cell daughterCell1 = daughterCells.get(0);
        Cell daughterCell2 = daughterCells.get(1);
    }


}
