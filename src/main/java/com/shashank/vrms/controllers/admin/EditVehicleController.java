package com.shashank.vrms.controllers.admin;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shashank.vrms.daos.BrandDAO;
import com.shashank.vrms.daos.VehicleDAO;
import com.shashank.vrms.enums.VehicleType;
import com.shashank.vrms.models.Brand;
import com.shashank.vrms.models.Vehicle;
import com.shashank.vrms.models.VehicleDocuments;

@WebServlet("/admin/vehicle/edit/*")
public class EditVehicleController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		int id = Integer.parseInt(path.substring(1));
		
		try {
		VehicleDAO vehicleDAO = new VehicleDAO();
		Vehicle vehicle = vehicleDAO.getVehicleById(id);
		BrandDAO brandDAO = new BrandDAO();
		List<Brand>brandList = brandDAO.getAllBrands();
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/editVehicle.jsp");
		request.setAttribute("vehicle", vehicle);
		request.setAttribute("brandList",brandList);
		rd.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			HttpSession session = request.getSession();
			session.setAttribute("msg","Something went wrong, Please try again");
			response.sendRedirect(request.getContextPath() +"/admin/vehicles");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String model = request.getParameter("model");
		String variant = request.getParameter("variant");
		String color = request.getParameter("color");
		String registrationNumber = request.getParameter("registrationNumber");
		String registrationYear = request.getParameter("registrationYear");
		String engineNumber = request.getParameter("engineNumber");
		String chasisNumber = request.getParameter("chasisNumber");
		int brandId = Integer.parseInt(request.getParameter("brandId"));
		int seatingCapacity = Integer.parseInt(request.getParameter("seatingCapacity"));
		String imageUrl = request.getParameter("imageUrl");
		boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));
		
		System.out.println(registrationNumber);
		Timestamp registrationExpirydate = Timestamp.valueOf(request.getParameter("registrationExpiryDate").replace("T"," ")+":00");
		Timestamp pucExpirydate = Timestamp.valueOf(request.getParameter("pucExpiryDate").replace("T"," ")+":00");
		Timestamp insuranceExpirydate = Timestamp.valueOf(request.getParameter("insuranceExpirydate").replace("T"," ")+":00");
		VehicleType type = VehicleType.valueOf(request.getParameter("type")) ;
		
		Vehicle vehicle = new Vehicle();
		VehicleDocuments documents = new VehicleDocuments();
		
		documents.setRegExpiresOn(registrationExpirydate);
		documents.setPucExpiresOn(pucExpirydate);
		documents.setInsuranceExpiresOn(insuranceExpirydate);
		documents.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
		System.out.println(documents.getUpdatedOn());
		vehicle.setId(id);
		vehicle.setModel(model);
		vehicle.setVariant(variant);
		vehicle.setColor(color);
		vehicle.setRegistrationNumber(registrationNumber);
		vehicle.setRegistrationYear(registrationYear);
		vehicle.setEngineNumber(engineNumber);
		vehicle.setChasisNumber(chasisNumber);
		vehicle.setBrandId(brandId);
		vehicle.setSeatingCapacity(seatingCapacity);
		vehicle.setAvailable(isAvailable);
		vehicle.setImageUrl(imageUrl);
		vehicle.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(vehicle.getUpdatedOn());
		
		vehicle.setType(type);
		vehicle.setDocuments(documents);
		try {
		VehicleDAO vehicleDAO = new VehicleDAO();
		vehicleDAO.updateVehicle(vehicle);
		vehicleDAO.updateVehicleDocuments(vehicle);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("msg", "Vehicle updated successfully...");
		response.sendRedirect(request.getContextPath() + "/admin/vehicles");
		}
		catch (SQLIntegrityConstraintViolationException e) {
			HttpSession session = request.getSession(false);
			session.setAttribute("msg", "Vehicle already exist...");
			response.sendRedirect(request.getContextPath() + "/admin/vehicles");	}
		catch (Exception e) {
			e.printStackTrace();
			HttpSession session = request.getSession(false);
			session.setAttribute("msg", "Something went wrong...");
			response.sendRedirect(request.getContextPath() + "/admin/vehicles");
		}
		
	}

}