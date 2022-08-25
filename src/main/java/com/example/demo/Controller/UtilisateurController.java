package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Utilisateur;
import com.example.demo.Repository.UtilisateurRepository;

import antlr.collections.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	//Avoir tous les utilisateurs
	
	@GetMapping("/utilisateurs")
	public java.util.List<Utilisateur> getAllUtilisateur(){
		
		return utilisateurRepository.findAll();
	}
	
	//Créer un utilisateur restApi
	@PostMapping("/utilisateurs")
	public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
		
		return utilisateurRepository.save(utilisateur);
	}
	
	//Avoir un utilisateur par son id RestApi
	@GetMapping("/utilisateurs/{id}")
	public ResponseEntity< Utilisateur> getUtilisateurById(@PathVariable Long id) {
		
		Utilisateur utilisateur = utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne  detient l'id :" + id));
		
		return ResponseEntity.ok(utilisateur);
	}
	
	//Mise à jour d'un utilisateur restApi
	@PutMapping("/utilisateurs/{id}")
	public ResponseEntity< Utilisateur> updateUtilisateur(@PathVariable Long id,@RequestBody Utilisateur utilisateurDetails){
		
		Utilisateur utilisateur = utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne  detient l'id :" + id));
		utilisateur.setNom(utilisateurDetails.getNom());
		utilisateur.setPrenom(utilisateurDetails.getPrenom());
		utilisateur.setEmail(utilisateurDetails.getEmail());
		utilisateur.setProfil(utilisateurDetails.getProfil());
		
		Utilisateur updUtilisateur = utilisateurRepository.save(utilisateur);
		return ResponseEntity.ok(updUtilisateur);
	}
	
	
	//Supprimer un utilisateur rest api
	@DeleteMapping("/utilisateurs/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUtilisateur(@PathVariable Long id){
		Utilisateur utilisateur = utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne  detient l'id :" + id));
		
		utilisateurRepository.delete(utilisateur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
	
	
	
}
