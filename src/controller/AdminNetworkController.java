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

import dao.NetworkDao;
import entity.Message;
import entity.Network;
import entity.Skill;

@Controller
@RequestMapping("/admincp")
public class AdminNetworkController {
	@Autowired
	private NetworkDao networkDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Mạng xã hội");
	}
	
	@RequestMapping("/network")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listNetwork", networkDao.getItems());
		return "admin.network.index";
	}
	
	@RequestMapping(value="/network/add", method=RequestMethod.GET)
	public String add() {
		return "admin.network.add";
	}
	
	@RequestMapping(value="/network/add", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objN") Network objN, BindingResult rs,RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "admin.network.add";
		}
		if (networkDao.addItem(objN) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/network";
	}
	
	@RequestMapping(value = "/network/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (networkDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/network";
	}
	
	@RequestMapping(value="/network/edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		Network network = networkDao.getItem(id);
		modelMap.addAttribute("objN", network);
		return "admin.network.edit";
	}
	
	@RequestMapping(value="/network/edit/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable("id") int id,@Valid @ModelAttribute("objN") Network objN,BindingResult rs, RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/network/edit/"+id;
		}
		objN.setId_net(id);
		if (networkDao.editItem(objN) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/network";
	}
}
