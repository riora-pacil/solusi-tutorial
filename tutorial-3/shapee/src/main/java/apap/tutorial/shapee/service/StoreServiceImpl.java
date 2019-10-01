package apap.tutorial.shapee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import apap.tutorial.shapee.repository.StoreDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.tutorial.shapee.model.StoreModel;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StoreServiceImpl implements StoreService{

	@Autowired
	StoreDb storeDb;

	@Override
	public StoreModel getStoreById(Long id){
		if(storeDb.findById(id).isPresent()){
			return storeDb.findById(id).get();
		}
		else{
			return null;
		}
	}
	@Override
	public void addStore(StoreModel storeModel){
		storeDb.save(storeModel);
	}
	@Override
	public List<StoreModel> getStoreList() {

		return storeDb.findAllByOrderByNamaAsc();
	}

	@Override
	public StoreModel changeStore(StoreModel store){
		//Mengambil objek store yang akan diubah dari database
		StoreModel targetStore = storeDb.findById(store.getId()).get();

		//Mengubah atribut
		targetStore.setNama(store.getNama());
		targetStore.setKeterangan(store.getKeterangan());
		storeDb.save(targetStore);
		return targetStore;
	}

	@Override
	public boolean deleteStore(StoreModel storeModel){
		if(storeModel.getListProduct().size()==0){
			storeDb.delete(storeModel);
			return true;
		}
		else{
			return false;
		}
	}


//	@Override
//	public StoreModel changeKeterangan(String idStore, String keterangan) {
//		for(StoreModel store : listStore) {
//			if(store.getId().equalsIgnoreCase(idStore)) {
//				store.setKeterangan(keterangan);
//				return store;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Boolean deleteStore(String idStore) {
//		for(StoreModel store : listStore) {
//			if(store.getId().equalsIgnoreCase(idStore)) {
//				listStore.remove(store);
//				return true;
//			}
//		}
//		return false;
//
//	}




}
