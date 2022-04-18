package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;


import rva.jpa.Igrac;
import rva.jpa.Nacionalnost;
import rva.jpa.Tim;


// jpa repositry je java predefinisana klasa koja sadrzi metode za komunikaciju sa bazom podataka 
public interface IgracRepository extends JpaRepository<Igrac,Integer>{

	Collection<Igrac> findByImeContainingIgnoreCase(String ime);



	Collection<Nacionalnost> findByNacionalnost(Nacionalnost igr);

}
