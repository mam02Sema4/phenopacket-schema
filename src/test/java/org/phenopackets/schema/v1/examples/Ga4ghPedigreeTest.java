package org.phenopackets.schema.v1.examples;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.ga4gh.pedigree.v1.Individual;
import org.ga4gh.pedigree.v1.Pedigree;
import org.ga4gh.pedigree.v1.Relationship;
import org.junit.jupiter.api.Test;
import org.phenopackets.schema.v1.core.OntologyClass;

public class Ga4ghPedigreeTest {

    public Pedigree trio() {
        Individual proband = Individual.newBuilder().setId("proband").build();
        Individual mother = Individual.newBuilder()
                .setId("mother")
                .setSex(ontologyClass("KIN:995", "Female"))
                .build();
        Individual father = Individual.newBuilder()
                .setId("father")
                .setSex(ontologyClass("KIN:996", "Male"))
                .build();

        Relationship probandMotherRelationship = Relationship.newBuilder()
                .setSubject("mother")
                .setRelationship(ontologyClass("KIN:003", "isBiologicalParent"))
                .setRelative("proband")
                .build();

        Relationship probandFatherRelationship = Relationship.newBuilder()
                .setSubject("father")
                .setRelationship(ontologyClass("KIN:003", "isBiologicalParent"))
                .setRelative("proband")
                .build();

        return Pedigree.newBuilder()
                .setProband("proband")
                .setConsultand("mother")
                .setDate("2021-02-18")
                .setReason(ontologyClass("OMIM:101600", "Apert syndrome"))
                .addIndividuals(proband)
                .addIndividuals(mother)
                .addIndividuals(father)
                .addRelationships(probandFatherRelationship)
                .addRelationships(probandMotherRelationship)
                .build();
    }

    @Test
    void testTrio() throws InvalidProtocolBufferException {
        System.out.println(JsonFormat.printer().print(trio()));
    }

    private OntologyClass ontologyClass(String id, String label) {
        return OntologyClass.newBuilder().setId(id).setLabel(label).build();
    }
}
