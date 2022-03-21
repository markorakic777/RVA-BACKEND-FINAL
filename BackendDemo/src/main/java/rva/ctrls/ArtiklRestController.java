package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Artikl;
import rva.repositories.ArtiklRepository;

// dependency injection tj injektovanje zavisnosti je injektovanje tj 


@RestController
public class ArtiklRestController {
	
	@Autowired
	private ArtiklRepository artiklRepository;
    @GetMapping("artikl")	
	public Collection<Artikl> getArtikli() {
		return artiklRepository.findAll();
		}

    @SuppressWarnings("deprecation")
	@GetMapping("artikl/{id}")
	public Artikl getArtikl(@PathVariable("id") Integer id) {
		return artiklRepository.getOne(id);
		}

}
