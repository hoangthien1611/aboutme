package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CatDao;
import dao.ContactDao;
import dao.NewsDao;
import dao.UserDao;

@Controller
@RequestMapping("/admincp")
public class AdminIndexController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private CatDao catDao;
	
	@Autowired
	private ContactDao contactDao;
	
	@RequestMapping("/index")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Trang chủ");
		modelMap.addAttribute("countCat", catDao.countItems());
		modelMap.addAttribute("countUser", userDao.countItems());
		modelMap.addAttribute("countNews", newsDao.getSumNews());
		modelMap.addAttribute("countContact", contactDao.countContact());
		return "admin.index.index";
	}
	
}
