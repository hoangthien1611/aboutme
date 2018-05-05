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

import dao.QuoteDao;
import entity.Message;
import entity.Quote;

@Controller
@RequestMapping("/admincp")
public class AdminQuoteController {
	public static final String DIR_UPLOAD = "files";
	@Autowired
	private QuoteDao quoteDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Danh ngôn");
	}
	
	@RequestMapping("/quote")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listQuote", quoteDao.getItems());
		return "admin.quote.index";
	}
	
	@RequestMapping(value = "/quote/add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		return "admin.quote.add";
	}
	
	@RequestMapping(value = "/quote/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objQ") Quote objQ, BindingResult rs, @RequestParam("hinhanh") CommonsMultipartFile cmf,
			RedirectAttributes ra, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "admin.quote.add";
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
		objQ.setPicture(cmf.getOriginalFilename());

		if (quoteDao.addItem(objQ) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/quote";
	}
	
	@RequestMapping(value = "/quote/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra, HttpServletRequest request) {
		String fileName = quoteDao.getItem(id).getPicture();
		if (!fileName.isEmpty()) {
			// có hình ảnh => xóa ảnh
			String filePath = request.getServletContext().getRealPath("/files") + File.separator + fileName;
			System.out.println(filePath);
			File file = new File(filePath);
			file.delete();
		}

		if (quoteDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/quote";
	}
	
	@RequestMapping(value = "/quote/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objQ", quoteDao.getItem(id));
		return "admin.quote.edit";
	}
	
	@RequestMapping(value = "/quote/edit/{id_quote}", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("objQ") Quote objQ, BindingResult rs, @RequestParam("hinhanh") CommonsMultipartFile cmf, @RequestParam(value="delPic", required=false) String delPic, 
			RedirectAttributes ra, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/quote/edit/"+objQ.getId_quote();
		}
		Quote oldQ = quoteDao.getItem(objQ.getId_quote());
		String filename = cmf.getOriginalFilename();
		if (!filename.isEmpty()) {
			if (!oldQ.getPicture().isEmpty()) {
				// xóa ảnh cũ
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldQ.getPicture();
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
			if ((delPic != null) && (!oldQ.getPicture().isEmpty())) {
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldQ.getPicture();
				File file = new File(filePath);
				file.delete();
			} else if (delPic == null) {
				filename = oldQ.getPicture();
			}
		}
		objQ.setPicture(filename);
		if (quoteDao.editItem(objQ) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/quote";
	}
}
