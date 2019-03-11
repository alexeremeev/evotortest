package ru.evotor.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "service_good_add_failed")
public class GoodAddFailed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endpoint_id", nullable = false)
    private Endpoint endpoint;
    private Timestamp date;
    @Column(name = "last_attempt")
    private Timestamp lastAttempt;

    public GoodAddFailed() {

    }

    public GoodAddFailed(String name, Unit unit, Endpoint endpoint, Timestamp date) {
        this.name = name;
        this.unit = unit;
        this.endpoint = endpoint;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getLastAttempt() {
        return lastAttempt;
    }

    public void setLastAttempt(Timestamp lastAttempt) {
        this.lastAttempt = lastAttempt;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }
}
