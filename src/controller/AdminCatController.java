package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.CatDao;
import entity.Cat;
import entity.Message;

@Controller
@RequestMapping("/admincp")
public class AdminCatController {
	@Autowired
	private CatDao catDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Danh mục");
	}
	
	@RequestMapping("/cat")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDao.getItems());
		return "admin.cat.index";
	}
	
	@RequestMapping(value="/cat/add", method=RequestMethod.GET)
	public String add() {
		return "admin.cat.add";
	}
	
	@RequestMapping(value="/cat/add", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objCat") Cat cat, BindingResult rs,RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "admin.cat.add";
		}
		if (catDao.addItem(cat) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/cat";
	}
	
	@RequestMapping(value="/cat/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (catDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/cat";
	}
	
	@RequestMapping(value="/cat/edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		Cat cat = catDao.getItem(id);
		modelMap.addAttribute("cat", cat);
		return "admin.cat.edit";
	}
	
	@RequestMapping(value="/cat/edit/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable("id") int id,@Valid @ModelAttribute("objCat") Cat cat,BindingResult rs, RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/cat/edit/"+id;
		}
		cat.setId_cat(id);
		if (catDao.editItem(cat) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/cat";
	}
}
