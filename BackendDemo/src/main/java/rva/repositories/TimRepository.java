package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;


import rva.jpa.Liga;

import rva.jpa.Tim;


// jpa repositry je java predefinisana klasa koja sadrzi metode za komunikaciju sa bazom podataka 
public interface TimRepository extends JpaRepository<Tim, Integer>{

	Collection<Tim> findByNazivContainingIgnoreCase(String naziv);

	Collection<Tim> findByLiga(Liga lig);






}
