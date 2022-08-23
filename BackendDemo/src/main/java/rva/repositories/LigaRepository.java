package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Liga;


// jpa repositry je java predefinisana klasa koja sadrzi metode za komunikaciju sa bazom podataka 
public interface LigaRepository extends JpaRepository<Liga, Integer>{

	Collection<Liga> findByNazivContainingIgnoreCase(String naziv);

}
