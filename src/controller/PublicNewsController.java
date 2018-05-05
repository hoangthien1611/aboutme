package controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseBody;

import constant.Defines;
import dao.CatDao;
import dao.CommentDao;
import dao.NetworkDao;
import dao.NewsDao;
import entity.Comment;
import entity.News;
import util.SlugUtil;
import util.StringUtil;

@Controller
public class PublicNewsController {
	@Autowired
	private CatDao catDao;
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private NetworkDao networkDao;
	
	@Autowired
	private SlugUtil slug;

	@Autowired
	private StringUtil stringUtil;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDao.getItems());
		modelMap.addAttribute("slugUtil", slug);
		modelMap.addAttribute("stringUtil", stringUtil);
		modelMap.addAttribute("listNewsMostViewed", newsDao.getItemsMostView());
		modelMap.addAttribute("listItemNews", newsDao.getItemsNew(5));
		modelMap.addAttribute("listCatRight", catDao.getItemsCountNews());
		modelMap.addAttribute("listNetwork", networkDao.getItems());
	}
	
	@RequestMapping("/{slug}/{cid}")
	public String cat(@PathVariable("slug") String slug, @PathVariable("cid") Integer cid,
			@RequestParam(value = "page", defaultValue = "1") Integer page, ModelMap modelMap) {
		if (cid == null) return "public.aboutme.index";
		if (page < 1) page = 1;
		int sumNews = newsDao.getSumNews(cid);
		int sumPage = (int) Math.ceil((float)sumNews/Defines.ROW_COUNT_PUBLIC);
		int offset = (page - 1) * Defines.ROW_COUNT_PUBLIC;
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
		modelMap.addAttribute("listNewsByCid", newsDao.getItemsByIDCat(offset, cid));
		modelMap.addAttribute("objCat", catDao.getItem(cid));
		modelMap.addAttribute("title", "Danh mục tin - "+catDao.getItem(cid).getName());
		return "public.aboutme.cat";
	}

	@RequestMapping("{cat}/{slug}/{id_news}.html")
	public String detail(@PathVariable("id_news") int id, ModelMap modelMap) {
		News objN = newsDao.getItem(id);
		if (objN == null) return "404";
		newsDao.increaseView(objN);
		modelMap.addAttribute("objN", objN);
		modelMap.addAttribute("listRelated", newsDao.getRelatedItems(objN));
		modelMap.addAttribute("listComment", commentDao.getItemsByIdNews(id));
		modelMap.addAttribute("title", objN.getName());
		return "public.aboutme.detail";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.POST)
	public @ResponseBody String detail(@Valid @ModelAttribute("objC") Comment objC, BindingResult rs, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "Vui lòng điền đầy đủ thông tin!";
		}
		String data = "";
		Date now = new Date(); 
		Timestamp ts = new Timestamp(now.getTime());
		objC.setDate_create(ts);
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		String date  = dateFormat.format(now);
		if (commentDao.addItem(objC) > 0) {
			if (objC.getId_parent() > 0) {
				data += "<div class=\"strator1\">";
			} else {
				data += "<div class=\"strator\">";
			}
			data += "<div class=\"strator-left\">" +
					"<img src=\""+request.getContextPath()+"/templates/public/images/co.png\" class=\"img-responsive\" >"+
				"</div>"+
				"<div class=\"strator-right\">"+
					"<h5>"+objC.getFullname()+"</h5>"+ 	
					"<span>"+date+"</span>"+
					"<p class=\"sin\">"+objC.getContent()+"</p>"+
					"<div class=\"reply\">"+
						"<a href=\"javscript:void(0)\" class=\"reply\" onclick=\"showRep("+objC.getId_parent()+")\">Trả lời</a>"+
					"</div>"+
				"</div>"+
			"<div class=\"clr\"></div>"+
		"</div>";
			return data;
		} else {
			return "Thất bại";
		}
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("searchText") String searchText, @RequestParam(value="searchCat", defaultValue="0") int idCat, @RequestParam(value="page", defaultValue="1") Integer page, ModelMap modelMap) {
		if (page == null || page < 1) {
			page = 1;
		}
		int offset = (page - 1) * Defines.ROW_COUNT_PUBLIC;
		int sumNews = 0;
		int sumPage = 1;
		if (idCat == 0) {
			sumNews = newsDao.countsumNewsBySearch(searchText);
			sumPage = (int) Math.ceil((double) sumNews / Defines.ROW_COUNT_PUBLIC);
			modelMap.addAttribute("listNews", newsDao.getItemsBySearch(searchText, offset));
		} else {
			sumNews = newsDao.countsumNewsBySearch(searchText, idCat);
			sumPage = (int) Math.ceil((double) sumNews / Defines.ROW_COUNT_PUBLIC);
			modelMap.addAttribute("listNews", newsDao.getItemsByIdCatSearch(searchText, idCat, offset));
		}

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
		modelMap.addAttribute("sText", searchText);
		modelMap.addAttribute("idCat", idCat);
		modelMap.addAttribute("title", "Tìm kiếm");
		return "public.aboutme.search";
	}
}
