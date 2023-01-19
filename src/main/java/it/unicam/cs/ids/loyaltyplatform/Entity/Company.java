package it.unicam.cs.ids.loyaltyplatform.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    Long id;
    String name;
    String address;
}
