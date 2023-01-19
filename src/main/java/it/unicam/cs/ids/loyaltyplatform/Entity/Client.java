package it.unicam.cs.ids.loyaltyplatform.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    String id;
    String name;
    String surname;
    String cellphoneNumber;
    String address;


}
