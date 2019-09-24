package apap.tutorial.gopud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import apap.tutorial.gopud.model.RestoranModel;

@Service
public class RestoranInMemoryService implements RestoranService {
	// Temporary list untuk menyimpan restoran
	private List<RestoranModel> listRestoran;
	
	// Constructor
	public RestoranInMemoryService() {
		listRestoran = new ArrayList<>();
	}

	@Override
	public void addRestoran(RestoranModel restoran) {
		listRestoran.add(restoran);
	}

	@Override
	public List<RestoranModel> getRestoranList() {
		return listRestoran;
	}

	@Override
	public RestoranModel getRestoranByIdRestoran(String idRestoran) {
		for (RestoranModel restoran:listRestoran) {
			if (restoran.getIdRestoran().equals(idRestoran)) {
				return restoran;
			}
		}
		return null;
	}
	
	// LATIHAN 2
	@Override
	public RestoranModel updateRestoranNomorTeleponById(String idRestoran, Integer nomorTelepon) {
		for (RestoranModel restoran:listRestoran) {
			if (restoran.getIdRestoran().equals(idRestoran)) {
				restoran.setNomorTelepon(nomorTelepon);
				return restoran;
			}
		}
		return null;
	}
	
	// LATIHAN 3
	@Override
	public void deleteRestoran(RestoranModel restoran) {
		listRestoran.remove(restoran);
	}
	

	
}
