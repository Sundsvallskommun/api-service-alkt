package se.sundsvall.alkt.integration.db.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PlainTextId implements Serializable {
}