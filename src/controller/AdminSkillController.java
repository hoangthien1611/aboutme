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

import dao.SkillDao;
import entity.Message;
import entity.Skill;

@Controller
@RequestMapping("/admincp")
public class AdminSkillController {
	@Autowired
	private SkillDao skillDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Kĩ năng");
	}
	
	@RequestMapping("/skill")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listSkill", skillDao.getItems());
		return "admin.skill.index";
	}
	
	@RequestMapping(value="/skill/add", method=RequestMethod.GET)
	public String add() {
		return "admin.skill.add";
	}
	
	@RequestMapping(value="/skill/add", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objS") Skill objS, BindingResult rs,RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "admin.skill.add";
		}
		if (skillDao.addItem(objS) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/skill";
	}
	
	@RequestMapping(value="/skill/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (skillDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/skill";
	}
	
	@RequestMapping(value="/skill/edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		Skill objS = skillDao.getItem(id);
		modelMap.addAttribute("objS", objS);
		return "admin.skill.edit";
	}
	
	@RequestMapping(value="/skill/edit/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable("id") int id,@Valid @ModelAttribute("objS") Skill objS,BindingResult rs, RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/skill/edit/"+id;
		}
		objS.setId_skill(id);
		if (skillDao.editItem(objS) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/skill";
	}
}
