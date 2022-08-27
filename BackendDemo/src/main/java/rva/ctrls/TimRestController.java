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

import io.swagger.annotations.ApiOperation;
import rva.jpa.Igrac;
import rva.jpa.Liga;
import rva.jpa.Nacionalnost;
import rva.jpa.Tim;
import rva.repositories.IgracRepository;
import rva.repositories.LigaRepository;
import rva.repositories.TimRepository;

// dependency injection tj injektovanje zavisnosti je injektovanje tj 

@CrossOrigin
@RestController
public class TimRestController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private TimRepository TimRepository;
	@Autowired
	private IgracRepository igracRepositroy;
	@Autowired
	private LigaRepository ligaRepositroy;
	
    @GetMapping("tim")	
    @ApiOperation(value = "Vraca sve timove iz baze")
	public Collection<Tim> getTimovi() {
		return TimRepository.findAll();
		}

    
	@GetMapping("tim/{id}")
	@ApiOperation(value = "Vraca tim iz baze po prosledjenom id-ju")
	public Tim getTim(@PathVariable("id") Integer id) {
		return TimRepository.getById(id);
		}

    
    @GetMapping("timNaziv/{naziv}")
    @ApiOperation(value = "Vraca tim iz baze podatka po prosledjenom nazivu")
	public Collection<Tim>  getTim(@PathVariable("naziv") String naziv) {
		return TimRepository.findByNazivContainingIgnoreCase(naziv);
		}



@PostMapping("tim")
@ApiOperation(value = "Unosi instacu TE tim u bazu podataka")
public ResponseEntity<Tim> InsertTim(@RequestBody Tim Tim)
{

if(!TimRepository.existsById(Tim.getId())) {
TimRepository.save(Tim);
return new ResponseEntity<Tim>(HttpStatus.OK);
	
}
return new ResponseEntity<Tim>(HttpStatus.CONFLICT);
}

@PutMapping("tim")
public ResponseEntity<Tim> UpdateTim(@RequestBody Tim Tim)
{

	if(TimRepository.existsById(Tim.getId())) {
		TimRepository.save(Tim);
		return new ResponseEntity<Tim>(HttpStatus.OK);
		}
		return new ResponseEntity<Tim>(HttpStatus.NO_CONTENT);
	
}



@GetMapping("timByLiga/{id}")
@ApiOperation(value = "Vraca tim po prosledjenom idju lige")
public Collection<Tim> getTimByLiga(@PathVariable("id") Integer id )
{
Liga lig= ligaRepositroy.getById(id);
return  TimRepository.findByLiga(lig);
}

@DeleteMapping("tim/{id}")
@ApiOperation(value = "Brise tim iz baze po proslejdneom id-ju")
public ResponseEntity<Tim> DeleteTim(@PathVariable("id") Integer id)
{

	if(TimRepository.existsById(id)) {
		TimRepository.deleteById(id);
		}
		
		if(id==-100)
	
		{
			if(!ligaRepositroy.existsById(-100))	jdbcTemplate.execute("insert into liga (id,naziv,oznaka) values (-100,'test','test')"); 
			jdbcTemplate.execute("insert into tim (id,naziv,osnovan,sediste,liga) values (-100,'test',to_date('01.03.1987', 'dd.mm.yyyy.'),'test',-100)");
	
		
			// klaasa ne sme da ima strani kljuc	
			return new ResponseEntity<Tim>(HttpStatus.OK);
		}
		

		
		return new ResponseEntity<Tim>(HttpStatus.NO_CONTENT);
	
}

}