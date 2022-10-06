package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Patient;
import com.example.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	
	//Avoir la liste de tous les patients
	@GetMapping("/patients")
	public List<Patient> getAllPatient(){
	
	return patientRepository.findAll();
	}
	
	//Créer un patient RestApi
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient) {
			
			return patientRepository.save(patient);
		}
	
	//Avoir un utilisateur par son Id RestApi
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
			
			Patient patient = patientRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Aucun patient ne  detient l'id :" + id));
			
			return ResponseEntity.ok(patient);
		}
	
	//Mise à jour d'un utilisateur RestApi
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id,@RequestBody Patient patientDetails){
			
			Patient patient = patientRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Aucun patient ne  detient l'id :" + id));
			patient.setNomPat(patientDetails.getNomPat());
			patient.setPrenomPat(patientDetails.getPrenomPat());
			patient.setAge(patientDetails.getAge());
			patient.setSexe(patientDetails.getSexe());
			patient.setTel(patientDetails.getTel());
			patient.setProfession(patientDetails.getProfession());
			patient.setNationalite(patientDetails.getNationalite());
			patient.setAdressePat(patientDetails.getAdressePat());
			patient.setMaladie(patientDetails.getMaladie());
			patient.setMedication(patientDetails.getMedication());
			patient.setContremedic(patientDetails.getContremedic());
			
			Patient updPatient = patientRepository.save(patient);
			return ResponseEntity.ok(updPatient);
		}
	
	//Supprimer un patient
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id){
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur ne  detient l'id :" + id));
		
		patientRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
}
