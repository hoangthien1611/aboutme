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

import dao.InterestDao;
import entity.Interest;
import entity.Message;

@Controller
@RequestMapping("/admincp")
public class AdminInterestController {
	@Autowired
	private InterestDao interestDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Quan tâm");
	}
	
	@RequestMapping("/interest")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listInterest", interestDao.getItems());
		return "admin.interest.index";
	}
	
	@RequestMapping(value="/interest/edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		Interest interest = interestDao.getItem(id);
		modelMap.addAttribute("objI", interest);
		return "admin.interest.edit";
	}
	
	@RequestMapping(value="/interest/edit/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable("id") int id,@Valid @ModelAttribute("objI") Interest objI,BindingResult rs, RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/interest/edit/"+id;
		}
		objI.setId_interest(id);
		if (interestDao.editItem(objI) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/interest";
	}
}
