package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Utilisateur;

public interface RegistrationRepository extends JpaRepository<Utilisateur, Long>{
	public Utilisateur findByEmail(String email);
	public Utilisateur findByEmailAndPassword(String email, String password);

}
