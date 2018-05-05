package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.ContactDao;
import entity.Message;

@Controller
@RequestMapping("/admincp")
public class AdminContactController {
	@Autowired
	private ContactDao contactDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Liên hệ");
	}
	
	@RequestMapping(value = { "/contact/{page}", "/contact" })
	public String index(@PathVariable(value = "page", required = false) Integer page, ModelMap modelMap) {
		if (page == null || page < 1) {
			page = 1;
		}
		
		int sumContact = contactDao.countContact();
		int sumPage = (int) Math.ceil((double) sumContact / Defines.ROW_COUNT_ADMIN);
		int offset = (page - 1) * Defines.ROW_COUNT_ADMIN;
		int pageEnd = (sumPage < 5)? sumPage : 5;
		int pageStart = 1;
		if (page > 3) {
			pageEnd = ((page + 2) < sumPage)? (page + 2) : sumPage;  
			pageStart = ((pageEnd - 4) < 1)? 1 : (pageEnd - 4);
		}
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("pageStart", pageStart);
		modelMap.addAttribute("pageEnd", pageEnd);
		modelMap.addAttribute("listContact", contactDao.getItems(offset));
		return "admin.contact.index";
	}
	
	@RequestMapping(value = "/contact/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (contactDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/contact";
	}

	@RequestMapping(value="/contact/delall")
	public String delAll(HttpServletRequest request, RedirectAttributes ra) {
		try {
			if(request.getParameterValues("id_contact") != null) {
				for (String index : request.getParameterValues("id_contact")) {
					int cid = Integer.parseInt(index);
					if(contactDao.delItem(cid) > 0) {
						Message msg = new Message(1, "Xóa thành công!");
						ra.addFlashAttribute("msg", msg);
					} else {
						Message msg = new Message(0, "Xóa thất bại!");
						ra.addFlashAttribute("msg", msg);
					}
				}
			}
			return "redirect:/admincp/contact";
		} catch (Exception e) {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
			return "redirect:/admincp/contact";
		}
	}
}
