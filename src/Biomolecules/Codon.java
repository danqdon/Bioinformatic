package Biomolecules;

import java.util.Objects;

public class Codon {
    private final Biomolecules.Base firstBase;
    private final Biomolecules.Base secondBase;
    private final Biomolecules.Base thirdBase;

    public Codon(Biomolecules.Base firstBase, Biomolecules.Base secondBase, Biomolecules.Base thirdBase) {
        this.firstBase = firstBase;
        this.secondBase = secondBase;
        this.thirdBase = thirdBase;
    }

    @Override
    public String toString() {
        return firstBase + "-" + secondBase + "-" + thirdBase;
    }

    public Biomolecules.Base getFirstBase() {
        return firstBase;
    }

    public Biomolecules.Base getSecondBase() {
        return secondBase;
    }

    public Biomolecules.Base getThirdBase() {
        return thirdBase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Codon codon = (Codon) o;
        return firstBase == codon.firstBase &&
                secondBase == codon.secondBase &&
                thirdBase == codon.thirdBase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstBase, secondBase, thirdBase);
    }

}
