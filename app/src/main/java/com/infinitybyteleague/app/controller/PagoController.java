package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Pago;
import com.infinitybyteleague.app.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class PagoController {
	private final PagoService pagoService;

	@Autowired
	public PagoController(PagoService pagoService) {
		this.pagoService = pagoService;
	}

	@GetMapping("/pagos")
	public List<Pago> getAllPagos() {
		return this.pagoService.getAll();
	}

	@PostMapping("/new-pago")
	public ResponseEntity<Pago> createPago(@RequestBody Pago newPago) {
		if (this.pagoService.findByFolioFactura(newPago.getFolioFactura()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pagoService.createPago(newPago));
	}

	@GetMapping("/pagos/{id}")
	public Pago PagoById(@PathVariable(name = "id") Integer id) {
		return this.pagoService.PagoById(id);
	}

	@DeleteMapping("/delete-pago/{id}")
	public void deletePago(@PathVariable(name = "id") Integer id) {
		this.pagoService.deletePago(id);
	}

	@PutMapping("/update-pagos/{id}")
	public ResponseEntity<?> updatePago(@PathVariable(name = "id") Integer id, @RequestBody Pago pagoDetails) {
		try {
			return ResponseEntity.ok(this.pagoService.updatePago(pagoDetails, id));
		} catch (AppNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/pago/folio/{folioFactura}")
    public ResponseEntity<Pago> findByFolioFactura(@PathVariable(name = "folioFactura") String folioFactura) {
        Pago pago = this.pagoService.findByFolioFactura(folioFactura);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

}