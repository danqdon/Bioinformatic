package ProteinSynthesis;

import Biomolecules.DNAStrand;

public interface DNADeserializer {
    DNAStrand deserialize(String dna);
}
