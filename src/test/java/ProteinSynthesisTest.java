import Biomolecules.DNAStrand;
import ProteinSynthesis.Exceptions.GeneSequenceNotFoundException;
import ProteinSynthesis.*;
import Serializers.TxtDNASerializer;
import Serializers.TxtPeptideSerializer;
import Serializers.TxtRNASerializer;
import Deserializers.TxtDNADeserializer;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ProteinSynthesisTest {

    @Test
    public void name() throws GeneSequenceNotFoundException {
        String sampleGenomeStrand = readGenomeFromFile("genome.txt");
        String sampleGeneStrand = readGenomeFromFile("gene.txt");

        if (sampleGenomeStrand == null || sampleGeneStrand == null) {
            return;
        }

        TxtDNADeserializer dnaDeserializer = new TxtDNADeserializer();
        DNAStrand genome = dnaDeserializer.deserialize(sampleGenomeStrand);
        DNAStrand gene = dnaDeserializer.deserialize(sampleGeneStrand);
        GeneLocator locator = new GeneLocator(genome);
        DNAStrand dnaStrand = locator.locate(gene);
        TxtDNASerializer dnaSerializer = new TxtDNASerializer();

        RNAPolymerase polymerase = new RNAPolymerase();
        MessengerRNA messengerRnaStrand = polymerase.transcription(dnaStrand);
        TxtRNASerializer rnaSerializer = new TxtRNASerializer();

        Ribosome ribosome = new Ribosome();
        Peptide peptidicChain = ribosome.translate(messengerRnaStrand);
        TxtPeptideSerializer peptideSerializer = new TxtPeptideSerializer();

        System.out.println("DNA Strand: " + dnaSerializer.serialize(dnaStrand));
        System.out.println("Genome: " + dnaSerializer.serialize(genome));
        System.out.println("Messenger RNA Strand: " + rnaSerializer.serialize(messengerRnaStrand));
        System.out.println("Peptidic Chain: " + peptideSerializer.serialize(peptidicChain));
    }

    private String readGenomeFromFile(String fileName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName))).toUpperCase();
            return content.replaceAll("\\s+", "");  // Removing any whitespaces
        } catch (Exception e) {
            System.err.println("Error reading the genome file: " + e.getMessage());
            return null;
        }
    }
}