package org.dreamteamdb.wigellsconcertjavafx.Entitys;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "wc")
public class WC {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        

        @Column(name = "name")
        private String name;

        @OneToMany(targetEntity = Customer.class, mappedBy = "wc", cascade = CascadeType.ALL)
        private List<Customer> customers;

        @OneToMany(targetEntity = Concert.class, mappedBy = "wc", cascade = CascadeType.ALL )
        private List<Concert> concerts;

        public WC() {
        }

        public WC(int id, String name, List<Customer> customers, List<Concert> concerts) {
                this.id = id;
                this.name = name;
                this.customers = customers;
                this.concerts = concerts;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public List<Customer> getCustomers() {
                return customers;
        }

        public void setCustomers(List<Customer> customers) {
                this.customers = customers;
        }

        public List<Concert> getConcerts() {
                return concerts;
        }

        public void setConcerts(List<Concert> concerts) {
                this.concerts = concerts;
        }

    


}
