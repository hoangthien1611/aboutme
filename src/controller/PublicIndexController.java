package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AdvertisementDao;
import dao.CatDao;
import dao.InterestDao;
import dao.NetworkDao;
import dao.NewsDao;
import dao.ProjectDao;
import dao.QuoteDao;
import dao.SkillDao;
import dao.WorkDao;
import util.SlugUtil;
import util.StringUtil;

@Controller
public class PublicIndexController {
	@Autowired
	private SlugUtil slug;

	@Autowired
	private StringUtil stringUtil;
	
	@Autowired
	private NetworkDao networkDao;
	
	@Autowired
	private CatDao catDao;
	
	@Autowired
	private InterestDao interestDao;
	
	@Autowired
	private WorkDao workDao;
	
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private QuoteDao quoteDao;
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private AdvertisementDao adDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDao.getItems());
		modelMap.addAttribute("slugUtil", slug);
		modelMap.addAttribute("stringUtil", stringUtil);
		modelMap.addAttribute("listNetwork", networkDao.getItems());
		modelMap.addAttribute("title", "Trang chủ");
	}
	
	@RequestMapping("/404")
	public String error404(){
		return "error/404";
	} 
	
	@RequestMapping("")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listInterest", interestDao.getItems());
		modelMap.addAttribute("listWork", workDao.getItems());
		modelMap.addAttribute("listSkill", skillDao.getItems());
		modelMap.addAttribute("listProject", projectDao.getItems());
		modelMap.addAttribute("listNews", newsDao.getItemsNew(3));
		modelMap.addAttribute("listQuoteRandom", quoteDao.getItemsRandom());
		modelMap.addAttribute("listAd", adDao.getItems());
		return "public.aboutme.index";
	}

}
