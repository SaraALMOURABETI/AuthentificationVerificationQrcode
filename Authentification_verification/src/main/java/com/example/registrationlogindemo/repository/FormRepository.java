package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.InscriptionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<InscriptionForm, Long> {

    // any custom repository methods can be added here
}
