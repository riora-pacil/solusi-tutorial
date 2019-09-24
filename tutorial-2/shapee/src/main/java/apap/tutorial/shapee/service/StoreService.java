package apap.tutorial.shapee.service;

import java.util.List;

import apap.tutorial.shapee.model.StoreModel;

public interface StoreService {
	
	//Method untuk menambah store
	void addStore(StoreModel store);
	
	//Method untuk mendapatkan data semua store yang tersimpan
	List<StoreModel> getStoreList();
	
	//Method untuk mendapatkan data store berdasarkan id
	StoreModel getStoreById(String idStore);


	//Latihan
	StoreModel changeKeterangan(String idStore, String keterangan);

	Boolean deleteStore(String idStore);
}