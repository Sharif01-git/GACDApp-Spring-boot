package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Utilisateur;
import com.example.demo.Repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository repo;

	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		
		return repo.save(utilisateur);
	}
	
	public Utilisateur fetchUtilisateurByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Utilisateur fetchUtilisateurByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}
}
