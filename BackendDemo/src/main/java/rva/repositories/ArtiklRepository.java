package rva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Artikl;


// jpa repositry je java predefinisana klasa koja sadrzi metode za komunikaciju sa bazom podataka 
public interface ArtiklRepository extends JpaRepository<Artikl, Integer>{

}
