package Serializers;

import Biomolecules.Codon;
import ProteinSynthesis.MessengerRNA;
import ProteinSynthesis.RNASerializer;

public class TxtRNASerializer implements RNASerializer {

    public String serialize(MessengerRNA messengerRnaStrand) {
        StringBuilder sb = new StringBuilder();
        for (Codon codon : messengerRnaStrand.getAnticodons()) {
            sb.append(codon.toString()).append(" ");
        }
        return sb.toString();
    }
}


