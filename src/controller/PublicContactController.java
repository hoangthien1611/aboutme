package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CatDao;
import dao.ContactDao;
import dao.NetworkDao;
import entity.Contact;
import util.SlugUtil;
import util.StringUtil;

@Controller
public class PublicContactController {
	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	private CatDao catDao;
	
	@Autowired
	private SlugUtil slug;

	@Autowired
	private StringUtil stringUtil;
	
	@Autowired
	private NetworkDao networkDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDao.getItems());
		modelMap.addAttribute("slugUtil", slug);
		modelMap.addAttribute("stringUtil", stringUtil);
		modelMap.addAttribute("listNetwork", networkDao.getItems());
		modelMap.addAttribute("title", "Liên hệ");
	}
	
	@RequestMapping(value="/lien-he", method=RequestMethod.GET)
	public String index(){
		return "public.aboutme.contact";
	}
	
	@RequestMapping(value="/lien-he", method=RequestMethod.POST)
	public @ResponseBody String index(@Valid @ModelAttribute("contact") Contact contact, BindingResult rs){
		if (rs.hasErrors()) {
			return "public.aboutme.contact";
		}
		if (contactDao.addItem(contact) > 0) {
			return "<span style=\"color:green; font-weight:bold\">Cảm ơn bạn đã gửi liên hệ!</span>";
		} else {
			return "<span style=\"color:red; font-weight:bold\">Gửi liên hệ thất bại. Vui lòng gửi lại!</span>";
		}
	}
}
