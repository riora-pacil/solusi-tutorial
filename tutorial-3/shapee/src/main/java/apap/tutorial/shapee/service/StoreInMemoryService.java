//package apap.tutorial.shapee.service;
//
//import apap.tutorial.shapee.model.StoreModel;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class StoreInMemoryService implements StoreService {
//	private List<StoreModel> listStore;
//
//
//	public StoreInMemoryService() {
//		listStore = new ArrayList<>();
//	}
//
//	@Override
//	public void addStore(StoreModel store) {
//		listStore.add(store);
//	}
//
//	@Override
//	public List<StoreModel> getStoreList() {
//		return listStore;
//	}
//
//
//
//
//	//ss sampai sini
//
//
//	@Override
//	public StoreModel getStoreById(Long idStore) {
//		// TODO Auto-generated method stub
////		for(StoreModel store : listStore) {
////			if(store.getId().equalsIgnoreCase(idStore)) {
////				return store;
////			}
////		}
//		return null;
//	}
//
//	@Override
//	public StoreModel changeStore(StoreModel storeModel) {
////		for(StoreModel store : listStore) {
////			if(store.getId().equalsIgnoreCase(idStore)) {
////				store.setKeterangan(keterangan);
////				return store;
////			}
////		}
//		return null;
//
//	}
//
////	@Override
////	public Boolean deleteStore(String idStore) {
////		for(StoreModel store : listStore) {
////			if(store.getId().equalsIgnoreCase(idStore)) {
////				listStore.remove(store);
////				return true;
////			}
////		}
////		return false;
////
////	}
//
//
//
//
//}
