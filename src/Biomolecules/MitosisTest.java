package Biomolecules;

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
        String sampleStrand = readGenomeFromFile();

        if (sampleStrand == null) {
            return;
        }

        TxtDNADeserializer dnaDeserializer = new TxtDNADeserializer();
        DNAStrand dnaStrand = dnaDeserializer.deserialize(sampleStrand);

        GenomeOrganizer organizer = new GenomeOrganizer();
        List<Chromatid> chromatidList = organizer.organizeIntoChromatids(dnaStrand);
        
        // Convert single chromatids into chromosomes
        List<Chromosome> initialChromosomes = new ArrayList<>();
        for (Chromatid chromatid : chromatidList) {
            initialChromosomes.add(new Chromosome(chromatid, null, chromatid.getId()));
        }

        // Create cell with single chromatid chromosomes
        Cell cell = new Cell(initialChromosomes);

        // Replicate DNA and produce chromosomes with two chromatids inside the cell
        cell.replicateDNA();

        // Divide the cell to produce two daughter cells
        Microtubule microtubule = new Microtubule();
        List<Cell> daughterCells = microtubule.divideCell(cell);
        Cell daughterCell1 = daughterCells.get(0);
        Cell daughterCell2 = daughterCells.get(1);
    }

    private String readGenomeFromFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("genome.txt"))).toUpperCase();
            return content.replaceAll("\\s+", "");  // Removing any whitespaces
        } catch (Exception e) {
            System.err.println("Error reading the genome file: " + e.getMessage());
            return null;
        }
    }
}
