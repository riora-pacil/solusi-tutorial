package apap.tutorial.gopud.service;

import java.util.List;

import apap.tutorial.gopud.model.RestoranModel;

public interface RestoranService {
    //Method untuk menambah restoran
    void addRestoran(RestoranModel restoran);

    //Method untuk mendapatkan semua data Restoran yang tersimpan
    List<RestoranModel> getRestoranList();

    //Method untuk mendapatkan data sebuah Restoran berdasarkan idRestoran
    RestoranModel getRestoranByIdRestoran(Long idRestoran);

    RestoranModel changeRestoran(RestoranModel restoranModel);

    void deleteRestoran(Long idRestoran);
}
