package rva.ctrls;

import java.util.Collection;

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

import rva.jpa.Liga;
import rva.jpa.Nacionalnost;
import rva.repositories.LigaRepository;
import rva.repositories.TimRepository;

// dependency injection tj injektovanje zavisnosti je injektovanje tj 

@CrossOrigin
@RestController
public class LigaRestController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private LigaRepository LigaRepository;
	
    @Autowired
	private TimRepository TimRepository;
	
	

    @GetMapping("liga")	
	public Collection<Liga> getLiga() {
		return LigaRepository.findAll();
		}

    @SuppressWarnings("deprecation")
	@GetMapping("liga/{id}")
	public Liga getLiga(@PathVariable("id") Integer id) {
		return LigaRepository.getById(id);
		}

    
    @GetMapping("ligaNaziv/{naziv}")
	public Collection<Liga>  getLiga(@PathVariable("naziv") String naziv) {
		return LigaRepository.findByNazivContainingIgnoreCase(naziv);
		}


@PostMapping("liga")
public ResponseEntity<Liga> InsertLiga(@RequestBody Liga liga)
{

if(!LigaRepository.existsById(	liga.getId())) {
LigaRepository.save(liga);
return new ResponseEntity<Liga>(HttpStatus.OK);
	
}
return new ResponseEntity<Liga>(HttpStatus.CONFLICT);
}

@PutMapping("liga")
public ResponseEntity<Liga> UpdateLiga(@RequestBody Liga Liga)
{

	if(LigaRepository.existsById(Liga.getId())) {
		LigaRepository.save(Liga);
		return new ResponseEntity<Liga>(HttpStatus.OK);
		}
		return new ResponseEntity<Liga>(HttpStatus.NO_CONTENT);
	
}








@DeleteMapping("liga/{id}")
public ResponseEntity<Liga> DeleteLiga(@PathVariable("id") Integer id)
{

	if(LigaRepository.existsById(id)) {
		LigaRepository.deleteById(id);
		return new ResponseEntity<Liga>(HttpStatus.OK); 
	                                  }
		
		if(id==-100)
		{
			jdbcTemplate.execute("insert into liga (id,naziv,oznaka) values (-100,'test','test')");
			return new ResponseEntity<Liga>(HttpStatus.OK);
		// klaasa ne sme da ima strani kljuc	
		}
		

		
		return new ResponseEntity<Liga>(HttpStatus.NO_CONTENT);
	
}

}