package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

}
