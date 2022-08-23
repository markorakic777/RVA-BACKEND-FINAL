package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Igrac;
import rva.jpa.Nacionalnost;
import rva.repositories.IgracRepository;
import rva.repositories.NacionalnostRepository;



@CrossOrigin
@RestController
public class NacionalnostRestController {
	
	@Autowired
	private NacionalnostRepository nacionalnostRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("Nacionalnost")
	public Collection<Nacionalnost> getNacionalnosti()
	{
		return nacionalnostRepository.findAll();
	}

	@GetMapping("Nacionalnost/{id}")
	public Nacionalnost getNacionalnost(@PathVariable("id") Integer id)
	{
		return nacionalnostRepository.getById(id);
	}
	
	@GetMapping("NacionalnostNaziv/{naziv}")
	
	public Collection<Nacionalnost> getNacionalnostByNaziv(@PathVariable("naziv") String naziv)
	{
		return nacionalnostRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("Nacionalnost")

	public ResponseEntity<Nacionalnost> insertNacionalnost(@RequestBody Nacionalnost nacionalnost)
	{
		if(!nacionalnostRepository.existsById(nacionalnost.getId()))
		{
			nacionalnostRepository.save(nacionalnost);
			return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Nacionalnost>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("Nacionalnost")

	public ResponseEntity<Nacionalnost> updateNacionalnost(@RequestBody Nacionalnost nacionalnost)
	{
		if(!nacionalnostRepository.existsById(nacionalnost.getId()))
		{
			return new ResponseEntity<Nacionalnost>(HttpStatus.NO_CONTENT);
		}
		nacionalnostRepository.save(nacionalnost);
		return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
	}
	

	@DeleteMapping("Nacionalnost/{id}")
	public ResponseEntity<Nacionalnost> deleteNacionalnost(@PathVariable Integer id)
	{
		if(!nacionalnostRepository.existsById(id))
		{
			return new ResponseEntity<Nacionalnost>(HttpStatus.NO_CONTENT);
		}
		
		jdbcTemplate.execute("DELETE FROM igrac WHERE nacionalnost = " + id);
		nacionalnostRepository.deleteById(id);
		if(id == -100) 
		{
		jdbcTemplate.execute("INSERT INTO \"nacionalnost\" (\"id\",\"naziv\",\"skracenica\")"
				+ "VALUES (-100,'Naziv Test','skr')");
		}
		return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
	}
	
}
