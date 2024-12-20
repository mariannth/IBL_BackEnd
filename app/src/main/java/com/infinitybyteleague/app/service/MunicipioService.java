package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Municipio;
import com.infinitybyteleague.app.repository.MunicipioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService {
	private final MunicipioRepository municipioRepository;

	@Autowired
	public MunicipioService(MunicipioRepository municipioRepository) {
		this.municipioRepository = municipioRepository;
	}

	public List<Municipio> getAll() {
		return municipioRepository.findAll();
	}

	public Municipio create(Municipio newMunicipio) {
		return this.municipioRepository.save(newMunicipio);
	}

	public Municipio MunById(Integer id) {
		return this.municipioRepository.findById(id).orElseThrow(() -> new AppNotFoundException(id));
	}

	public Municipio findByMun(String NombreMunicipio) {
		return this.municipioRepository.findByNombreMunicipio(NombreMunicipio);
	}

	public void deleteMunicipio(Integer id) {
		if (this.municipioRepository.existsById(id)) {
			this.municipioRepository.deleteById(id);
		} else {
			throw new AppNotFoundException(id);
		}
	}

	public Municipio updateMunicipio(Municipio municipio, Integer id) {
		return this.municipioRepository.findById(id).map(munField -> {
			munField.setNombreMunicipio(municipio.getNombreMunicipio());
			return this.municipioRepository.save(munField);
		}).orElseThrow(() -> new AppNotFoundException(id));
	}

}