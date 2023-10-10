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
        DNAPolymerase dnaPolymerase = new DNAPolymerase();
        //TODO añadir cromatida complementaria y luego meter ambas en el cromosoma
        List<Chromosome> chromosomes = new ArrayList<>();
        for (Chromatid chromatid : chromatidList)
            chromosomes.add(new Chromosome(chromatid, chromatid.getId()));

        Cell cell = new Cell(chromosomes, dnaPolymerase); //TODO adaptar la clase para que admita que un cariotipo
                                                          // tenga un par de cromosomas por elemento
        cell.replicateDNA(); //TODO cambiar el replicate para que duplique en 2 cromosomas, y no haga complementario
        Microtubule microtubule = new Microtubule(dnaPolymerase);
        List<Cell> daughterCells = microtubule.divideCell(cell); //TODO separa cada elemento del cariotipo y crea dos celulas con cromosomas individuales
        Cell daughterCell1 = daughterCells.get(0);
        Cell daughterCell2 = daughterCells.get(1);
        DNAStrandCellSerializer genomeSerializer = new DNAStrandCellSerializer();
        assertThat(genomeSerializer.serialize(cell)).isEqualTo(Files.readString(Path.of("genome.txt")).toUpperCase());
    }


}
