import Biomolecules.DNAStrand;
import Deserializers.TxtDNADeserializer;
import Mitosis.*;
import Serializers.DNAStrandCellSerializer;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MitosisTest {

    @Test
    public void should_Create_Two_Identical_Daughter_Cells_From_One() throws IOException {
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
        DNAStrandCellSerializer genomeSerializer = new DNAStrandCellSerializer();
        assertThat(genomeSerializer.serialize(cell)).isEqualTo(Files.readString(Path.of("genome.txt")).toUpperCase());
    }


}
