package CellCycle.Mitosis;
import Biomolecules.Base;
import Biomolecules.Codon;
import Biomolecules.DNAStrand;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DNAPolymerase{
    private static final Map<Base, Base> COMPLEMENTARY_MAP = Map.of(
            Base.ADENINE, Base.THYMINE,
            Base.THYMINE, Base.ADENINE,
            Base.CYTOSINE, Base.GUANINE,
            Base.GUANINE, Base.CYTOSINE
    );

    public DNAStrand getComplementary(DNAStrand dnaStrand) {
        List<Codon> originalCodons = dnaStrand.codons;
        List<Codon> complementaryCodons = new ArrayList<>();

        for (Codon codon : originalCodons) {
            Base complementaryFirstBase = COMPLEMENTARY_MAP.get(codon.getFirstBase());
            Base complementarySecondBase = COMPLEMENTARY_MAP.get(codon.getSecondBase());
            Base complementaryThirdBase = COMPLEMENTARY_MAP.get(codon.getThirdBase());

            Codon complementaryCodon = new Codon(complementaryFirstBase, complementarySecondBase, complementaryThirdBase);
            complementaryCodons.add(complementaryCodon);
        }

        return new DNAStrand(complementaryCodons);
    }
}
