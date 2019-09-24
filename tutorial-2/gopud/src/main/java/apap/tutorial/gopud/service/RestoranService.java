package apap.tutorial.gopud.service;

import java.util.List;

import apap.tutorial.gopud.model.RestoranModel;

public interface RestoranService {
	// Abstract method untuk "menambah restoran"
	void addRestoran(RestoranModel restoran);
	
	// Abstract method untuk "melihat semua restoran yang telah ditambahkan"
	List<RestoranModel> getRestoranList();
	
	// Abstract method untuk "mendapatkan sebuah restoran berdasarkan idRestoran"
	RestoranModel getRestoranByIdRestoran(String idRestoran);
	
	// LATIHAN 2
	RestoranModel updateRestoranNomorTeleponById(String idRestoran, Integer nomorTelepon);

	// LATIHAN 3
	void deleteRestoran(RestoranModel restoran);
}
