package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.InscriptionForm;
import com.example.registrationlogindemo.repository.FormRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/inscription-forms")
public class InscriptionFormController {
    @Autowired
    private FormRepository formRepository;



    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("inscriptionForm", new InscriptionForm());
        return "my-page";
    }


    @PostMapping("/create")
    public String createInscriptionForm(@RequestParam("profilePicFile") MultipartFile profilePicFile,
                                        @RequestParam("backgroundPicFile") MultipartFile backgroundPicFile,
                                        @RequestParam("galleryFile") MultipartFile galleryFile,
                                         InscriptionForm inscriptionForm,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return "inscription-forms/create";
        }

        if (!profilePicFile.isEmpty()) {
            inscriptionForm.setProfilePic(saveFile(profilePicFile));
        }

        if (!backgroundPicFile.isEmpty()) {
            inscriptionForm.setBackgroundPic(saveFile(backgroundPicFile));
        }

        if (!galleryFile.isEmpty()) {
            inscriptionForm.setGallery(saveFile(galleryFile));
        }

        formRepository.save(inscriptionForm);
        redirectAttributes.addFlashAttribute("successMessage", "Inscription form created successfully.");
        return "list";
        //return "redirect:/inscription-forms";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        InscriptionForm inscriptionForm = formRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inscription form ID: " + id));

        model.addAttribute("inscriptionForm", inscriptionForm);
        //return "inscription-forms/create";
        return "my-page";
    }

    @GetMapping("/{id}/delete")
    public String deleteInscriptionForm(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        InscriptionForm inscriptionForm = formRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inscription form ID: " + id));

        formRepository.delete(inscriptionForm);
        redirectAttributes.addFlashAttribute("successMessage", "Inscription form deleted successfully.");
        return "redirect:/inscription-forms/create";
    }



    @GetMapping("/users/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<InscriptionForm> listUsers = formRepository.findAll();

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            String[] csvHeader = {"Profile Pic", "Background Pic", "Nom", "Prenom", "Numero", "Email", "Nom Societe", "Poste",
                    "About", "Siteweb", "Instagram", "LinkedIn", "Phone", "Insta Company", "Facebook Company",
                    "LinkedIn Company", "Genre", "Gallery"};
            csvWriter.writeNext(csvHeader);

            for (InscriptionForm inscriptionForm : listUsers) {
                String[] rowData = {String.valueOf(inscriptionForm.getProfilePic()), String.valueOf(inscriptionForm.getBackgroundPic()), inscriptionForm.getNom(), inscriptionForm.getPrenom(),
                        inscriptionForm.getNumero(), inscriptionForm.getEmail(), inscriptionForm.getNomSociete(), inscriptionForm.getPoste(), inscriptionForm.getAbout(),
                        inscriptionForm.getSiteweb(), inscriptionForm.getInstagram(), inscriptionForm.getLinkedIn(), inscriptionForm.getPhone(), inscriptionForm.getInstaCompany(),
                        inscriptionForm.getFacebookCompany(), inscriptionForm.getLinkedInCompany(), inscriptionForm.getGenre(), String.valueOf(inscriptionForm.getGallery())};
                csvWriter.writeNext(rowData);
            }
        }
    }


    private File saveFile(MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        File file = new File("C:\\Users\\THOCHIBA\\Pictures\\AdquestQr\\" + fileName);
        //      C:\Users\THOCHIBA\Pictures\AdquestQr
        multipartFile.transferTo(file);
        return file;
    }



}
