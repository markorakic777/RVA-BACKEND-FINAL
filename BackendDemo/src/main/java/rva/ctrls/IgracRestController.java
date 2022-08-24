package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import rva.jpa.Igrac;
import rva.jpa.Liga;
import rva.jpa.Nacionalnost;
import rva.jpa.Tim;
import rva.repositories.IgracRepository;
import rva.repositories.LigaRepository;
import rva.repositories.NacionalnostRepository;
import rva.repositories.TimRepository;



@CrossOrigin 
@RestController

public class IgracRestController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //sta je ovi
	
	
	@Autowired
	private IgracRepository igracRepository;
	@Autowired
	private TimRepository timRepository;



	@Autowired
	private NacionalnostRepository nacionalnostRepository;


	@Autowired
	private LigaRepository ligaRepository;



 
	@GetMapping("igrac")	
	
	public Collection<Igrac> getIgrac() {
		return igracRepository.findAll();
		}

    @SuppressWarnings("deprecation") // ovo znaci da mozemo koristtii starije sintkase
	@GetMapping("igrac/{id}")
	public Igrac getIgrac(@PathVariable("id") Integer id) {
		return igracRepository.getById(id);
		}

    
    @GetMapping("igracNaziv/{ime}")
	public Collection<Igrac>  getIgrac(@PathVariable("ime") String ime) {
		return igracRepository.findByImeContainingIgnoreCase(ime);
		}

    


@PostMapping("igrac")
public ResponseEntity<Igrac> InsertIgrac(@RequestBody Igrac igrac)
{

if(!igracRepository.existsById(igrac.getId())) {
igracRepository.save(igrac);
return new ResponseEntity<Igrac>(HttpStatus.OK);
	
}
return new ResponseEntity<Igrac>(HttpStatus.CONFLICT);
}

@PutMapping("igrac")
public ResponseEntity<Igrac> Updateigrac(@RequestBody Igrac igrac)
{

	if(igracRepository.existsById(igrac.getId())) {
		igracRepository.save(igrac);
		return new ResponseEntity<Igrac>(HttpStatus.OK);
		}
		return new ResponseEntity<Igrac>(HttpStatus.NO_CONTENT);
	
}


@GetMapping("IgracByNacionalnost/{id}")
public Collection<Igrac> getIgracByNacionalnost(@PathVariable("id") Integer id )
{
Nacionalnost nac= nacionalnostRepository.getById(id);
return igracRepository.findByNacionalnost(nac);
}


@GetMapping("igracByTim/{id}")
public Collection<Igrac> getIgracByTim(@PathVariable("id") Integer id )
{
Tim tim= timRepository.getById(id);
return igracRepository.findByTim(tim);
}



@DeleteMapping("igrac/{id}")
public ResponseEntity<Igrac> DeleteIgrac(@PathVariable("id") Integer id)
{

	if(igracRepository.existsById(id)) {
		igracRepository.deleteById(id);
		return new ResponseEntity<Igrac>(HttpStatus.OK);
		}
		
		if(id==-100)
		{
			



		if(!nacionalnostRepository.existsById(-100))	jdbcTemplate.execute("insert into nacionalnost (id,naziv,skracenica) values (-100,'test','test')");
			
		if(!ligaRepository.existsById(-100))    jdbcTemplate.execute("insert into liga (id,naziv,oznaka) values (-100,'test','test')");

			jdbcTemplate.execute("insert into igrac (id,ime,prezime,broj_reg,datum_rodjenja,nacionalnost,tim) values (-100,'test','test','test',to_date('01.03.1987', 'dd.mm.yyyy.'),-100,-100)");
			
			return new ResponseEntity<Igrac>(HttpStatus.OK);
			// klaasa ne sme da ima strani kljuc	
		}
		
		
		
		return new ResponseEntity<Igrac>(HttpStatus.NO_CONTENT);
	
}

}