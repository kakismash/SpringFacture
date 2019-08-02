package com.kaki.springboot.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kaki.springboot.app.models.entity.Client;
import com.kaki.springboot.app.models.service.IClientService;
import com.kaki.springboot.app.models.service.IUploadFileService;
import com.kaki.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	
	@GetMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> seeImage(@PathVariable String filename){
		
		Resource resource = null;
		
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().
				header(HttpHeaders.CONTENT_DISPOSITION, "atachment: filename=\"" + resource.getFilename() + "\"" ).
				body(resource);
		
	}
	
	@GetMapping(value="/see/{id}")
	public String see(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash){
		
		Client client = clientService.findOne(id);
		if(client == null) {
			flash.addFlashAttribute("error", "This client doesnt exist in the database");
			return "redirect:/list";
		}
		
		model.put("client", client);
		model.put("title", "Client details of: " + client.getName());
		
		
		return "see";
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page,  Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Client> clients = clientService.findAll(pageRequest);
		
		
		PageRender<Client> pageRender = new PageRender<>("/list", clients);
		model.addAttribute("title", "Client List");
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "list";
	}
	
	@GetMapping(value="/form")
	public String create(Map<String, Object> model) {
		Client client = new Client();
		model.put("client", client);
		model.put("button", "Create Client");
		model.put("title", "Client Form");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
	
		Client client = null;
		if(id > 0) {
			client = clientService.findOne(id);
			if(client == null) {
				flash.addFlashAttribute("error", "Client ID doesn't exist!");
				return "redirect:/list";
			}
		}else {
			flash.addFlashAttribute("error", "Client ID cant be 0!");
			return "redirect:/list";
		}
		model.put("client", client);
		model.put("button", "Edit Client");
		model.put("title", "Edit Client");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String safe(@Valid Client client, BindingResult result, Model model, @RequestParam("file") MultipartFile fil, RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Formulario de Cliente");
			return "form";
		}
		
		if(!fil.isEmpty()) {
//			Path resourcesDirectory = Paths.get("src//main//resources//static//uploads");
//			String rootPath = resourcesDirectory.toFile().getAbsolutePath();
			
//			String rootPath = "C://Temp//uploads";
			
			if(client.getId() != null && client.getId() > 0 && client.getImage().length() > 0) {
				uploadFileService.delete(client.getImage());
			}
			String uniqueFileName = null;
			try {
				uniqueFileName = uploadFileService.copy(fil);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flash.addFlashAttribute("info", "the image was succesfull uploaded '" + uniqueFileName + "'");
			
			client.setImage(uniqueFileName);
			
			}
		
		String flashMessage = (client.getId() != null)? "Client edited!" : "Client Created!";
		
		clientService.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", flashMessage);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if(id > 0) {
			
			Client client = clientService.findOne(id);
			
			clientService.delete(id);
			flash.addFlashAttribute("success", "Client Deleted!");
			
			if(uploadFileService.delete(client.getImage())) {
					flash.addFlashAttribute("info", "Image " + client.getImage() + "was deleted successfull");
				}
				
				
			
			
			
		}
		return "redirect:/list";
	}
}
