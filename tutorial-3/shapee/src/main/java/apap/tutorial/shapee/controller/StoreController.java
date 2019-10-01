package apap.tutorial.shapee.controller;

import java.util.List;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.service.StoreService;

@Controller
public class StoreController {
	@Qualifier("storeServiceImpl")
	@Autowired
	private StoreService storeService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	private String home(){
		return "home";
	}

	//API yang digunakan untuk mengakses halaman add store
	@RequestMapping(value = "/store/add", method = RequestMethod.GET)
	public String addStoreFormPage( Model model) {
		StoreModel newStore = new StoreModel();
		newStore.setFollowers(0);
		model.addAttribute("store", newStore);
		return "form-add-store";

	}

	//API yang digunakan untuk submit form yang telah anda masukan di halaman add store
	@RequestMapping(value="/store/add", method = RequestMethod.POST)
	private String addStoreSubmit(@ModelAttribute StoreModel store, Model model){
		storeService.addStore(store);
		model.addAttribute("nama", store.getNama());
		return "add-store";
	}

	//Ubah method view anda seperti dibawah ini
	//API untuk mengakses halaman view store
	@RequestMapping("/store/view")
	public String view(
			//Request Parameter untuk dipass
			@RequestParam(value="idStore") Long idStore, Model model
	) {
		//Mengambil objek store yang dituju berdasarkan id
		StoreModel store = storeService.getStoreById(idStore);
		if(store!=null){
			//Add objek store ke "store" untuk dirender
			model.addAttribute("store",store);
			List<ProductModel> productList = productService.findAllProductByStoreId(store.getId());
			model.addAttribute("productList", productList);
			//Return view template view-store
			return "view-store";
		}
		else{
			model.addAttribute("id", idStore);
			return "store-not-found";
		}



	}

	//API yang digunakan untuk menuju halaman form change store
	@RequestMapping(value="store/change/{idStore}", method = RequestMethod.GET)
	public String changeStoreFormPage(@PathVariable Long idStore, Model model) {
		//Mengambil existing data store
		StoreModel existingStore = storeService.getStoreById(idStore);
		if(existingStore!=null){
			model.addAttribute("store", existingStore);
			return "form-change-store";
		}
		else{
			model.addAttribute("id", idStore);
			return "store-not-found";
		}


	}
	//API yang digunakan untuk submit form change store
	@RequestMapping(value="store/change/{idStore}", method = RequestMethod.POST)
	public String changeStoreFormSubmit(@PathVariable Long idStore, @ModelAttribute StoreModel store, Model model) {
		StoreModel newStoreData = storeService.changeStore(store);
		model.addAttribute("store", newStoreData);
		return "change-store";
	}


	//tutor 3 ss

	//URL Mapping View
	@RequestMapping("/store/add")
	public String add(
			//Request parameter untuk dipass
			@RequestParam(value= "idStore", required=true) String idStore,
			@RequestParam(value= "nama", required=true) String nama,
			@RequestParam(value= "keterangan", required=true) String keterangan,
			@RequestParam(value= "followers", required=true) int followers,
			Model model
	) {
		return "add-store";
	}



	//URL Mapping viewAll
	@RequestMapping(value="/store/view-all")
	public String viewAll(Model model) {

		//Mengambil data semua objek restoran yang ada
		List<StoreModel> storeList = storeService.getStoreList();

		//Add objek store ke "storeList" untuk dirender
		model.addAttribute("storeList", storeList);

		//Return view template view-all-store
		return "view-all-store";
	}

	@RequestMapping(value="store/delete/id-store/{idStore}")
	public String deleteWithPathVar(@PathVariable Long idStore, Model model) {
		StoreModel storeTarget =  storeService.getStoreById(idStore);
		if(storeTarget!=null){
			if(storeService.deleteStore(storeTarget)==true){
				return "delete-store";
			}
			else{
				model.addAttribute("id", idStore);
				return "delete-store-failed";
			}
		}
		else{
			return "store-not-found";
		}
	}

	//tutorial 2 sampai sini


	//JAWABAN LATIHAN TUTORIAL 2

//	@RequestMapping(value="store/view/id-store/{idStore}")
//	public String viewWithPathVar(@PathVariable Long idStore, Model model) {
//		StoreModel store = storeService.getStoreById(idStore).get();
//		if(store!=null) {
//			model.addAttribute("store", store);
//			return "view-store";
//		}
//		else {
//			model.addAttribute("id", idStore);
//			return "store-not-found";
//		}
//	}

//	@RequestMapping(value="store/view/id-store/{idStore}/keterangan/{keterangan}")
//	public String changeWithPathVar(@PathVariable String idStore, @PathVariable String keterangan, Model model) {
//		StoreModel store = storeService.changeKeterangan(idStore, keterangan);
//		if(store!=null) {
//			model.addAttribute("nama", store.getNama());
//			return "change-store";
//		}
//		else {
//			model.addAttribute("id", idStore);
//			return "store-not-found";
//		}
//	}
//
//	@RequestMapping(value="store/delete/id-store/{idStore}")
//	public String deleteWithPathVar(@PathVariable String idStore, Model model) {
//		Boolean delete = storeService.deleteStore(idStore);
//		if(delete==true){
//			return "delete-store";
//		}
//		else{
//			model.addAttribute("id", idStore);
//			return "store-not-found";
//		}
//
//	}



	
}