package com.apfmiranda.backendpedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apfmiranda.backendpedidos.domain.Estado;
import com.apfmiranda.backendpedidos.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAllByOrderByNome() {
		return repo.findAllByOrderByNome();
	}

}
