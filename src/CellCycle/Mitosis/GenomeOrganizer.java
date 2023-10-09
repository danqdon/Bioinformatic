package CellCycle.Mitosis;

import Biomolecules.Codon;
import Biomolecules.DNAStrand;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GenomeOrganizer {
    public List<Chromatid> organizeIntoChromatids(DNAStrand dnaStrand){
        int numberOfChromosomes = 23;
        List<Chromatid> chromatidArray = new ArrayList<Chromatid>(numberOfChromosomes);
        int chromatidSize = dnaStrand.codons.size()/numberOfChromosomes;
        for (int i = 0;i<numberOfChromosomes;i++){
            int chromatidStart = i*chromatidSize;
            int chromatidEnd = chromatidStart+chromatidSize;
            DNAStrand strand = new DNAStrand(dnaStrand.codons.subList(chromatidStart,chromatidEnd));
            chromatidArray.add(new Chromatid(strand,i));
        }
        return chromatidArray;
    }
}
