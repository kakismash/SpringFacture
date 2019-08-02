package com.kaki.springboot.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kaki.springboot.app.models.entity.Client;
import com.kaki.springboot.app.models.entity.Facture;
import com.kaki.springboot.app.models.entity.ItemFacture;
import com.kaki.springboot.app.models.entity.Product;
import com.kaki.springboot.app.models.service.IClientService;

@Controller
@RequestMapping("/facture")
@SessionAttributes("facture")
public class FactureController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping("see/{id}")
	public String see(	@PathVariable(value = "id") Long id,
						Model model,
						RedirectAttributes flash) {
		Facture facture = clientService.findFactureById(id);
		if(facture == null) {
			flash.addFlashAttribute("error", "This facture does not exist on the DB!");
			return "redirect:/list";
		}
		
		model.addAttribute("facture", facture);
		model.addAttribute("title", "Facture: " .concat(facture.getDescription()));
		
		return "facture/see";
	}
	
	
	@GetMapping("/form/{clientId}")
	public String create(@PathVariable(value = "clientId") Long clientId, Map<String, Object> model, RedirectAttributes flash) {
		
		Client client = clientService.findOne(clientId);
		if(client == null) {
			flash.addFlashAttribute("error", "This client doesnt exist in the database");
			return "redirect:/list";
		}
		
		Facture facture = new Facture();
		facture.setClient(client);
		model.put("facture", facture);
		model.put("title", "Create Facture");
		
		
		return "facture/form";
	}
	
	@GetMapping(value="/load-products/{term}", produces = {"application/json"})
	public @ResponseBody List<Product> loadProducts(@PathVariable String term){
		return clientService.findByName(term);
	}
	
	@PostMapping("/form")
	public String save(	@Valid Facture facture,
						BindingResult result,
						Model model,
						@RequestParam(name="item_id[]", required=false) Long[] itemId,
						@RequestParam(name="quantity[]", required=false) Integer[] quantity, 
							RedirectAttributes flash,
							SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Create facture");
			return "facture/form";
		}
		
		if(itemId == null || itemId.length == 0) {
			model.addAttribute("title", "Create Facture");
			model.addAttribute("error", "Error: The facture must contains at less one line");
			return "facture/form";
		}
		
		for(int i = 0; i < itemId.length; i++) {
			Product product = clientService.findProductById(itemId[i]);
			ItemFacture line = new ItemFacture();
			line.setQuantity(quantity[i]);
			line.setProduct(product);
			facture.addItemsFacture(line);
			
		}
		
		clientService.saveFacture(facture);
		status.setComplete();
		flash.addFlashAttribute("success", "Facture saved successfull" );
		
		return "redirect:/see/" + facture.getClient().getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Facture facture = clientService.findFactureById(id);
		if(facture != null) {
			clientService.deleteFacture(id);
			flash.addFlashAttribute("success", "Facture deleted!");
			return "redirect:/see/" + facture.getClient().getId();
		}
		flash.addFlashAttribute("error", "This facture was not found it!");
		
		return "redirect:/list";
	}
	
}
