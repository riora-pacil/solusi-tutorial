package apap.tutorial.gopud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController {
	@Autowired
	private RestoranService restoranService;

	// URL Mapping "Add Restoran"
	@RequestMapping("/restoran/add")
	public String add(
			// Request parameter untuk dipass
			@RequestParam(value = "idRestoran", required = true) String idRestoran,
			@RequestParam(value = "nama", required = true) String nama,
			@RequestParam(value = "alamat", required = true) String alamat,
			@RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
			Model model
			) {

		// Membuat objek RestoranModel dari paramter yang dipass
		RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);

		// Memanggil service "addRestoran"
		restoranService.addRestoran(restoran);

		// Add variabel nama restoran ke "namaResto"
		model.addAttribute("namaResto", nama);

		// Return view template
		return "add-restoran";
	}

	// URL Mapping "View Restoran by ID"
	@RequestMapping("/restoran/view")
	public String view(
			// Request parameter untuk dipass
			@RequestParam(value = "idRestoran", required = true) String idRestoran,
			Model model
			) {

		// Null checking for idRestoran 
		if (restoranService.getRestoranByIdRestoran(idRestoran) != null) {
			// Memanggil service "getRestoranByIdRestoran"			
			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

			// Add objek restoran ke "resto"
			model.addAttribute("resto", restoran);

			// Return view template
			return "view-restoran";
		}
		else {
			return "view-restoran-error";
		}
	}

	// URL Mapping "View All Restoran"
	@RequestMapping("/restoran/viewall")
	public String viewall(Model model)
	{
		// Memanggil service "getRestoranList"			
		List<RestoranModel> listRestoran = restoranService.getRestoranList();

		// Add listRestoran ke listResto
		model.addAttribute("listResto", listRestoran);

		// Return view template
		return "view-all-restoran";
	}

	// LATIHAN 1
	@RequestMapping(value = "/restoran/view/id-restoran/{idRestoran}")
	public String viewpath(
			@PathVariable(value = "idRestoran", required = true) String idRestoran,
			Model model) {

		// Null checking for idRestoran 
		if (restoranService.getRestoranByIdRestoran(idRestoran) != null) {
			// Memanggil service "getRestoranByIdRestoran"			
			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

			// Add objek restoran ke "resto"
			model.addAttribute("resto", restoran);

			// Return view template
			return "view-restoran";
		}
		// Return halaman error
		else {
			return "view-restoran-error";
		}
	}

	// LATIHAN 2
	@RequestMapping(value = "/restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
	public String update(
			@PathVariable(value = "idRestoran", required = true) String idRestoran,
			@PathVariable(value = "nomorTelepon", required = true) Integer nomorTelepon,
			Model model) {

		// Null checking for idRestoran 
		if (restoranService.getRestoranByIdRestoran(idRestoran) != null) {
			// Memanggil service "updateRestoranNomorTeleponById"			
			RestoranModel restoran = restoranService.updateRestoranNomorTeleponById(idRestoran, nomorTelepon);

			// Add objek restoran ke "resto"
			model.addAttribute("namaResto", restoran.getNama());

			// Return view template
			return "update-restoran";
		}
		// Return halaman error
		else {
			return "update-restoran-error";
		}
	}
	
	// LATIHAN 2
		@RequestMapping(value = "/restoran/delete/id/{idRestoran}")
		public String delete(
				@PathVariable(value = "idRestoran", required = true) String idRestoran,
				Model model) {

			// Null checking for idRestoran 
			if (restoranService.getRestoranByIdRestoran(idRestoran) != null) {
				// Memanggil service "getRestoranByIdRestoran"			
				RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
				
				// Memanggil service "deleteRestoran"			
				restoranService.deleteRestoran(restoran);

				// Add objek restoran ke "resto"
				model.addAttribute("namaResto", restoran.getNama());

				// Return view template
				return "delete-restoran";
			}
			// Return halaman error
			else {
				return "delete-restoran-error";
			}
		}
}

