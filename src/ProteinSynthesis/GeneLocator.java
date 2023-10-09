package ProteinSynthesis;

import Biomolecules.Codon;
import Biomolecules.DNAStrand;
import ProteinSynthesis.Exceptions.GeneSequenceNotFoundException;

import java.util.List;

public class GeneLocator {
    private DNAStrand genome;

    public GeneLocator(DNAStrand genome) {
        this.genome = genome;
    }


    public DNAStrand locate(DNAStrand geneStrand) throws GeneSequenceNotFoundException {
        int geneLength = geneStrand.codons.size();
        for (int i = 0; i <= genome.codons.size() - geneLength; i++) {
            if (areCodonListsEqual(genome.codons.subList(i, i + geneLength), geneStrand.codons)) {
                return new DNAStrand(genome.codons.subList(i, i + geneLength));
            }
        }
        throw new GeneSequenceNotFoundException("Provided gene sequence not found in the DNA strand.");
    }

    private boolean areCodonListsEqual(List<Codon> list1, List<Codon> list2) {
        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            Codon codon1 = list1.get(i);
            Codon codon2 = list2.get(i);
            if (!areCodonsEqual(codon1, codon2))
                return false;
        }
        return true;
    }

    private boolean areCodonsEqual(Codon codon1, Codon codon2) {
        return codon1.getFirstBase() == codon2.getFirstBase() &&
                codon1.getSecondBase() == codon2.getSecondBase() &&
                codon1.getThirdBase() == codon2.getThirdBase();
    }

}
