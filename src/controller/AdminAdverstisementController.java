package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.AdvertisementDao;
import entity.Advertisement;
import entity.Message;

@Controller
@RequestMapping("/admincp")
public class AdminAdverstisementController {
	public static final String DIR_UPLOAD = "files";
	@Autowired
	private AdvertisementDao adDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Quảng cáo");
	}
	
	@RequestMapping("/advertisement")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listAd", adDao.getItems());
		return "admin.advertisement.index";
	}
	
	@RequestMapping(value = "/advertisement/add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		return "admin.advertisement.add";
	}
	
	@RequestMapping(value = "/advertisement/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objAd") Advertisement objAd, BindingResult rs, @RequestParam("hinhanh") CommonsMultipartFile cmf,
			RedirectAttributes ra, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "admin.advertisement.add";
		}
		if (!cmf.getOriginalFilename().isEmpty()) {
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_UPLOAD;
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			try {
				cmf.transferTo(new File(dirPath + File.separator + cmf.getOriginalFilename()));
				System.out.println("Upload thành công!");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

		}
		objAd.setPicture(cmf.getOriginalFilename());

		if (adDao.addItem(objAd) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/advertisement";
	}
	
	@RequestMapping(value = "/advertisement/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra, HttpServletRequest request) {
		String fileName = adDao.getItem(id).getPicture();
		if (!fileName.isEmpty()) {
			// có hình ảnh => xóa ảnh
			String filePath = request.getServletContext().getRealPath("/files") + File.separator + fileName;
			System.out.println(filePath);
			File file = new File(filePath);
			file.delete();
		}

		if (adDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/advertisement";
	}
	
	@RequestMapping(value = "/advertisement/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objAd", adDao.getItem(id));
		return "admin.advertisement.edit";
	}
	
	@RequestMapping(value = "/advertisement/edit/{id_ad}", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("objAd") Advertisement objAd, BindingResult rs, @RequestParam("hinhanh") CommonsMultipartFile cmf, @RequestParam(value="delPic", required=false) String delPic, 
			RedirectAttributes ra, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/advertisement/edit/"+objAd.getId_ad();
		}
		Advertisement oldAd = adDao.getItem(objAd.getId_ad());
		String filename = cmf.getOriginalFilename();
		if (!filename.isEmpty()) {
			if (!oldAd.getPicture().isEmpty()) {
				// xóa ảnh cũ
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldAd.getPicture();
				File file = new File(filePath);
				file.delete();
			}
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_UPLOAD;
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			try {
				cmf.transferTo(new File(dirPath + File.separator + filename));
				System.out.println("Upload thành công!");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			if ((delPic != null) && (!oldAd.getPicture().isEmpty())) {
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldAd.getPicture();
				File file = new File(filePath);
				file.delete();
			} else if (delPic == null) {
				filename = oldAd.getPicture();
			}
		}
		objAd.setPicture(filename);
		if (adDao.editItem(objAd) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/advertisement";
	}
}
