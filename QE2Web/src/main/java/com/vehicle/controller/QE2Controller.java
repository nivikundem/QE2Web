package com.vehicle.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.DOMException;

import com.bridge.crossing.model.CrossingActivityObjModel;
//import com.bridge.pdfgenerator.PdfProcessor;
import com.qe2.crossing.CrossingDetailsMessageSender;
import com.qe2.messaging.MessageSender;
import com.token.api.clients.RestClient;
import com.token.model.TokenClientModel;
import com.vehicle.entities.VehicleRegistrationDetails;
import com.vehicle.service.VehicleRegistrationDetailsService;

//http://localhost:8080/QE2Web/VehicleRegistrationView

@Controller
public class QE2Controller {

	public final static String BASE_URL = "http://localhost:8080/ProxamaAPI/ProxamaAPIApplication/api/";

	private static final Logger LOG = Logger.getLogger(QE2Controller.class
			.getName());

	@Autowired
	private VehicleRegistrationDetailsService vehicleRegistrationDetailsService;

		
    @RequestMapping("/VehicleRegistrationView")
    public String listVehicleRegistrationDetails(Map<String, Object> map) { 
        map.put("vehicleRegistrationDetails", new VehicleRegistrationDetails());
        map.put("vehicleRegistrationDetailsList", vehicleRegistrationDetailsService.listVehicleRegistrationDetails()); 
        return "VehicleRegistrationView";
    }
    
    @RequestMapping(value = "/ActivityView", method = RequestMethod.GET)
	public String getCrossingDetailsList(
			@RequestParam(required = false) String quickSearchTextbox,
			@RequestParam(required = false) String sortField,	
			@RequestParam(required = false) String vrn,	
			@RequestParam(required = false) String direction,	
			@RequestParam(required = false) String gateNumber,	
			ModelMap model) throws SQLException {

    	System.out.println("vrn   "+vrn);
    	System.out.println("direction   "+direction);
    	System.out.println("gateNumber   "+gateNumber);
    	
    	if(isObjectNotNull(vrn)&&isObjectNotNull(direction)&&isObjectNotNull(gateNumber)){
    		if(vrn.trim().length()>0){
				vrn = vrn.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
			}			
			
			CrossingDetailsMessageSender.CrossingDetails(vrn, gateNumber,direction);
    	}
    	
		String quickSearchTextboxVar = "ALL";
		if (quickSearchTextbox != null) {
			quickSearchTextboxVar = quickSearchTextbox;
		}
		String sortString = "crossing_datetime";
		if (sortField != null) {
			sortString = sortField;
		}	
		model.addAttribute("crossingDetailsObjList", CrossingActivityObjModel
				.getActivityDetailsResultList(quickSearchTextboxVar, sortString
						));
		return "ActivityView";
	}
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addVehicleRegistrationDetails(@ModelAttribute("vehicleRegistrationDetails")
    VehicleRegistrationDetails vehicleRegistrationDetail, BindingResult result) { 
    	vehicleRegistrationDetailsService.addVehicleRegistrationDetails(vehicleRegistrationDetail); 
        return "redirect:/VehicleRegistrationView";
    }
    
    @RequestMapping("/delete/{uid}")
    public String deleteVehicleRegistrationDetails(@PathVariable("uid")
    Integer uid) {
    	vehicleRegistrationDetailsService.removeVehicleRegistrationDetails(uid);
        return "redirect:/VehicleRegistrationView";
    } 
       
    @RequestMapping(value="/edit")
    public String editVehicleRegistrationDetails(@RequestParam(required = false)  Integer uid,@RequestParam(required = false) String ddlVehicleType) {       
    	VehicleRegistrationDetails vehicleRegistrationDetails = vehicleRegistrationDetailsService.findVehicleRegistrationDetailsById(uid);
    	vehicleRegistrationDetails.setVehicleType(ddlVehicleType);
        vehicleRegistrationDetailsService.updateEmployee(vehicleRegistrationDetails);      
        return "redirect:/VehicleRegistrationView";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {     
    return "loginView";
     
    }
    
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "deniedView";
    }
    
    @RequestMapping(value = "/ContactView", method = RequestMethod.GET)
	public String getContactView(Model model) {
		return "ContactView";
	}

	@RequestMapping(value = "/AboutView", method = RequestMethod.GET)
	public String getAboutView(Model model) {
		return "AboutView";
	}
	
	
	
	
	@RequestMapping(value = "/TokenRegistrationView", method = RequestMethod.GET)
	public String getTokenDetails(	
			@RequestParam(required = false) String registrationField,
			@RequestParam(required = false) String token,
			@RequestParam(required = false) String key,
			@RequestParam(required = false) String value,
			@RequestParam(required = false) String searchToken,
			@RequestParam(required = false) String searchKey,			
			ModelMap model) throws SQLException {

	
		if(isObjectNotNull(registrationField)){
			System.out.println("registrationField   "+registrationField);
			model.addAttribute("registrationClientResponse", RestClient.invokeRestClient(BASE_URL+"registration"));
		}		
		model.addAttribute("registrationObjList", TokenClientModel.getRegistrationList());	
		if(isObjectNotNull(token)&&isObjectNotNull(key)&&isObjectNotNull(value)){
			model.addAttribute("saveClientResponse", RestClient.invokeRestClient(BASE_URL+"store/"+token+"/"+key+"/"+value));
		}
		String searchUrl = BASE_URL;
		
		if(isObjectNotNull(searchToken) && isObjectNotNull(searchKey) && !searchKey.isEmpty()){
			searchUrl = searchUrl+"retrieve/"+searchToken+"/"+searchKey;
			model.addAttribute("searchClientResponse", RestClient.invokeRestClient(searchUrl));
		}
		else if(isObjectNotNull(searchToken)){
			searchUrl = searchUrl+"retrieve/"+searchToken;
			model.addAttribute("searchClientResponseMap", RestClient.invokeSearchClient(searchUrl));
		}
		
		return "TokenRegistrationView";
	}
	
	
	
	@RequestMapping(value = "/SendMessageView", method = RequestMethod.GET)
	public String sendActiveMqMessage(Model model) {	
		
		MessageSender messageSender = new MessageSender();
		String message = null;
		try {
			message = messageSender.sendMessage();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		model.addAttribute("message", message);		
		return "SendMessageView";
	}
	
	
	
/*
	@RequestMapping(value = "/PdfGenView", method = RequestMethod.POST)
	public String getPdfGenView(
			@RequestParam(required = false) String quickSearchTextbox,
			@RequestParam(required = false) String sortField,
			@RequestParam("pdf") int[] selectedPdfs,
			Model model) throws SQLException {
		
		String quickSearchTextboxVar = "ALL";
		if (quickSearchTextbox != null) {
			quickSearchTextboxVar = quickSearchTextbox;
		}
		System.out.println("sortField    in controller   " + sortField);
		LOG.info("sortField  "+sortField);
		String sortString = "crossing_datetime";
		if (sortField != null) {
			sortString = sortField;
		}
		PdfProcessor pdfProcessor = new PdfProcessor();
		if (selectedPdfs != null) {
				LOG.info("Pdfs for invoices are generating ............. ");				
				for (int selectedPdf : selectedPdfs) {
					LOG.info("pdf :: " + selectedPdf);
					pdfProcessor.processSelectedInvoices(selectedPdf);
					LOG.info("Pdf generated for  ::::  " + selectedPdf);
					model.addAttribute("crossingDetailsObjList", CrossingActivityObjModel
							.getActivityDetailsResultList(quickSearchTextboxVar, sortString
									));				
					model.addAttribute("selectedPdfs", selectedPdfs);				
				return "ActivityView";
			} 
		}
		return "ActivityView";
	}*/
	
	
	private boolean isObjectNotNull(Object obj){
		if(obj != null){
			return true;
		}
		else{
			return false;
		}
	}	
}

