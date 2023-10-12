package Deserializers;

import Biomolecules.Base;
import Biomolecules.Codon;
import Biomolecules.DNAStrand;
import ProteinSynthesis.DNADeserializer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TxtDNADeserializer implements DNADeserializer {

    private static final Map<Character, Base> BASE_MAP = Map.of(
            'A', Base.ADENINE,
            'T', Base.THYMINE,
            'C', Base.CYTOSINE,
            'G', Base.GUANINE,
            'U', Base.URACIL
    );

    public static Base mapToBase(char baseChar) {
        Base base = BASE_MAP.get(baseChar);
        if (base == null) {
            throw new IllegalArgumentException("Invalid nitrogen base: " + baseChar);
        }
        return base;
    }
    @Override
    public DNAStrand deserialize(String file) {
        String dnaStrand = readGenomeFromFile(file);
        List<Codon> codons = new ArrayList<>();
        int length = dnaStrand.length() - dnaStrand.length() % 3;
        System.out.println(length);

        for (int i = 0; i < length; i += 3) {
            if (i + 3 <= length) {
                Base first = mapToBase(dnaStrand.charAt(i));
                Base second = mapToBase(dnaStrand.charAt(i+1));
                Base third = mapToBase(dnaStrand.charAt(i+2));
                codons.add(new Codon(first, second, third));
            }

        }
        return new DNAStrand(codons);
    }

    private String readGenomeFromFile(String file) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file))).toUpperCase();
            return content.replaceAll("\\s+", "");  // Removing any whitespaces
        } catch (Exception e) {
            System.err.println("Error reading the genome file: " + e.getMessage());
            return null;
        }
    }
}
