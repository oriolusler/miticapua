package com.oriolsoler.pua.entities;

import com.oriolsoler.pua.repository.StringListConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "pua")
public class Pua {

    public static List<String> PUEROS = Arrays.asList("Erik", "Pol", "Buti", "Jaume", "Alberto", "Obi", "Suli");

    @Id
    @Column(length = 36)
    private UUID id = UUID.randomUUID();

    @Column(length = 2500)
    private String description;

    private LocalDate occurredAt;
    @Convert(converter = StringListConverter.class)
    @Column(name = "pueros", nullable = false)
    private List<String> pueros = new ArrayList<>();

    @Convert(converter = StringListConverter.class)
    @Column(name = "victims", nullable = false)
    private List<String> victims = new ArrayList<>();

    private String reporter;

    // Hibernate expects entities to have a no-arg constructor,
    // though it does not necessarily have to be public.
    public Pua() {
    }

    public Pua(String description, LocalDate occurredAt, List<String> pueros, List<String> victims, String reporter) {
        this.description = description;
        this.occurredAt = occurredAt;
        this.pueros = pueros;
        this.victims = victims;
        this.reporter = reporter;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getOccurredAt() {
        return occurredAt;
    }

    public List<String> getPueros() {
        return pueros;
    }

    public List<String> getVictims() {
        return victims;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setOccurredAt(LocalDate occurredAt) {
        this.occurredAt = occurredAt;
    }

    public void setPueros(List<String> pueros) {
        this.pueros = pueros;
    }

    public void setVictims(List<String> victims) {
        this.victims = victims;
    }

}