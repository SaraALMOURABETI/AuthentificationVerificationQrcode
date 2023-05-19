package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.InscriptionForm;
import com.example.registrationlogindemo.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;

@Service
public class InscriptionFormService {

    private FormRepository FormRepository;
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    public InscriptionFormService(FormRepository inscriptionFormRepository) {
        this.FormRepository = inscriptionFormRepository;
    }

    public InscriptionForm save(InscriptionForm inscriptionForm) {
        return FormRepository.save(inscriptionForm);
    }

/*
    public InscriptionForm addImage(String Nom, String Prenom, String numero, String  , float oldprice, boolean likedByUser, Categorie categorie, MultipartFile image, String status) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectory(uploadPath);
        }
        // Generate a unique filename for the image
        String filename = nameProduct.replaceAll("\\s+", "") +"." + FilenameUtils.getExtension(image.getOriginalFilename());
        // Save the image to the uploads directory
        Files.copy(image.getInputStream(), Paths.get(UPLOAD_DIR, filename), StandardCopyOption.REPLACE_EXISTING);
        Product product = new Product(nameProduct,description,fulldescription,price,oldprice,filename,status,likedByUser,categorie);
        // Create a new product with the name, image filename, and default values for the other fields

        return productRepositry.save(product);
    }
*/
/*
    public Product UpdateProduct(Long productId, String nameProduct, String description, String fulldescription, float price, float oldprice, boolean likedByUser, Categorie categorie, MultipartFile image, String status) throws IOException {
        Optional<Product> optionalProduct = productRepositry.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setNameProduct(nameProduct);
            product.setDescription(description);
            product.setFulldescription(fulldescription);
            product.setPrice(price);
            product.setOldprice(oldprice);
            product.setLikedByUser(likedByUser);
            product.setCategorie(categorie);
            product.setStatus(status);
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }
            if (image != null && !image.isEmpty()) {
                // Generate a unique filename for the image
                String filename = nameProduct.replaceAll("\\s+", "") +"." + FilenameUtils.getExtension(image.getOriginalFilename());
                // Save the image to the uploads directory
                Files.copy(image.getInputStream(), Paths.get(UPLOAD_DIR, filename), StandardCopyOption.REPLACE_EXISTING);
                product.setImage(filename);
            }
            return productRepositry.save(product);
        }
        return null;
    }
*/

    /*public InscriptionForm addImage(MultipartFile backgroundPic, MultipartFile gallery, MultipartFile profilePic) throws Exception {
        // Process and save the image files as needed
        String backgroundPicUrl = saveImage(backgroundPic);
        String galleryUrl = saveImage(gallery);
        String profilePicUrl = saveImage(profilePic);

        // Create a new InscriptionForm object
        InscriptionForm inscriptionForm = new InscriptionForm();
        inscriptionForm.setBackgroundPic(backgroundPicUrl);
        inscriptionForm.setGallery(galleryUrl);
        inscriptionForm.setProfilePic(profilePicUrl);

        // Save the InscriptionForm object or perform any other necessary operations

        return inscriptionForm;
    }*/

    private String saveImage(MultipartFile image) throws Exception {
        if (image != null && !image.isEmpty()) {
            // Implement the logic to save the image to a specific location or storage service
            // Return the URL or path of the saved image file
            // Example code:
            // String savedImagePath = storageService.saveImage(image);
            // return savedImagePath;
        }
        return null;
    }


        public List<InscriptionForm> listAll() {
            return FormRepository.findAll(Sort.by("email").ascending());
        }



    // Define any other service methods here, if needed
}
