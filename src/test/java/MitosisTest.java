import Biomolecules.DNAStrand;
import Deserializers.TxtDNADeserializer;
import Mitosis.*;
import Serializers.TxtCellSerializer;
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
        DNAStrand dnaStrand = dnaDeserializer.deserialize("gene.txt");
        GenomeOrganizer organizer = new GenomeOrganizer();
        List<Chromatid> chromatidList = organizer.organizeIntoChromatids(dnaStrand);
        DNAPolymerase dnaPolymerase = new DNAPolymerase();
        List<KaryotypeElement> karyotypeElements = new ArrayList<>();
        List<Chromosome> chromosomes = new ArrayList<>();
        for (Chromatid chromatid : chromatidList) {
            Chromatid complementaryChromatid = new Chromatid(dnaPolymerase.getComplementary(chromatid).getDna(), chromatid.getId());
            Chromosome chromosome = new Chromosome(chromatid, complementaryChromatid, chromatid.getId());
            chromosomes.add(chromosome);
            karyotypeElements.add(new KaryotypeElement(chromosome,chromosome.getId()));
        }
        DNALigase dnaLigase = new DNALigase(dnaPolymerase);
        Cell cell = new Cell(karyotypeElements, dnaPolymerase, dnaLigase);
        TxtCellSerializer genomeSerializer = new TxtCellSerializer();
        cell = cell.getDnaLigase().replicate(cell);
        //Microtubule microtubule = new Microtubule(dnaPolymerase);
        //List<Cell> daughterCells = microtubule.divideCell(cell); //TODO separa cada elemento del cariotipo y crea dos celulas con cromosomas individuales
        //Cell daughterCell1 = daughterCells.get(0);
        //Cell daughterCell2 = daughterCells.get(1);
        //assertThat(genomeSerializer.serialize(cell)).isEqualTo(Files.readString(Path.of("gene.txt")).toUpperCase());
        assertThat(genomeSerializer.serialize(cell)).isEqualTo(Files.readString(Path.of("gene.txt")).toUpperCase());
    }


}
