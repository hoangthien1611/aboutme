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

import dao.WorkDao;
import entity.Message;
import entity.Work;

@Controller
@RequestMapping("/admincp")
public class AdminWorkController {
	@Autowired
	private WorkDao workDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Việc làm");
	}
	
	@RequestMapping("/work")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listWork", workDao.getItems());
		return "admin.work.index";
	}
	
	@RequestMapping(value="/work/add", method=RequestMethod.GET)
	public String add() {
		return "admin.work.add";
	}
	
	@RequestMapping(value="/work/add", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objW") Work objW, BindingResult rs,RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "admin.work.add";
		}
		if (workDao.addItem(objW) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/work";
	}
	
	@RequestMapping(value="/work/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (workDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/work";
	}
	
	@RequestMapping(value="/work/edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		Work objW = workDao.getItem(id);
		modelMap.addAttribute("objW", objW);
		return "admin.work.edit";
	}
	
	@RequestMapping(value="/work/edit/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable("id") int id,@Valid @ModelAttribute("objW") Work objW,BindingResult rs, RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/work/edit/"+id;
		}
		objW.setId_work(id);
		if (workDao.editItem(objW) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/work";
	}
}
