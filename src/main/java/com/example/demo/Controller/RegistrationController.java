package com.example.demo.Controller;

import javax.imageio.spi.RegisterableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Utilisateur;
import com.example.demo.Service.RegistrationService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registration")
	public Utilisateur registerUser(@RequestBody Utilisateur  utilisateur) throws Exception {
		String tempEmail = utilisateur.getEmail();
		if(tempEmail != null && !"".equals(tempEmail)) {
			Utilisateur utilisateurObj = service.fetchUtilisateurByEmail(tempEmail);
			if(utilisateurObj != null) {
				throw new Exception("L'utilisateur avec comme email: "+tempEmail+" existe déjà");
			}
		}
		Utilisateur utilisateurObj = null;
		utilisateurObj = service.saveUtilisateur(utilisateur);
		return utilisateurObj;
	}
	
	@PostMapping("/login")
	public Utilisateur  loginUser(@RequestBody Utilisateur utilisateur) throws Exception {
		String tempEmail = utilisateur.getEmail();
		String tempPassword = utilisateur.getPassword();
		Utilisateur utilisateurObj = null;
		if(tempEmail != null && tempPassword != null) {
			 utilisateurObj = service.fetchUtilisateurByEmailAndPassword(tempEmail, tempPassword);
		}
		if(utilisateurObj == null) {
			throw new Exception("Cet utilisateur n'existe pas");
		}
		return utilisateurObj;
	}
}
