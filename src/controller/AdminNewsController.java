package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.CatDao;
import dao.NewsDao;
import dao.UserDao;
import entity.Message;
import entity.News;
import entity.User;

@Controller
@RequestMapping("/admincp")
public class AdminNewsController {
	public static final String DIR_UPLOAD = "files";

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private CatDao catDao;

	@Autowired
	private UserDao userDao;

	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDao.getItems());
		modelMap.addAttribute("title", "Admin - Bài viết");
	}

	@RequestMapping(value = { "/news/{page}", "/news" })
	public String index(@PathVariable(value = "page", required = false) Integer page, ModelMap modelMap) {
		if (page == null || page < 1) {
			page = 1;
		}

		int sumNews = newsDao.getSumNews();
		int sumPage = (int) Math.ceil((double) sumNews / Defines.ROW_COUNT_ADMIN);
		int offset = (page - 1) * Defines.ROW_COUNT_ADMIN;
		int pageEnd = (sumPage < 5) ? sumPage : 5;
		int pageStart = 1;
		if (page > 3) {
			pageEnd = ((page + 2) < sumPage) ? (page + 2) : sumPage;
			pageStart = ((pageEnd - 4) < 1) ? 1 : (pageEnd - 4);
		}
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("pageStart", pageStart);
		modelMap.addAttribute("pageEnd", pageEnd);
		modelMap.addAttribute("listNews", newsDao.getItems(offset));
		return "admin.news.index";
	}

	@RequestMapping(value = "/news/add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		return "admin.news.add";
	}

	@RequestMapping(value = "/news/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objN") News objN, BindingResult rs,
			@RequestParam("hinhanh") CommonsMultipartFile cmf, RedirectAttributes ra, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "admin.news.add";
		}
		if (!cmf.getOriginalFilename().isEmpty()) {
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_UPLOAD;
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			try {
				cmf.transferTo(new File(dirPath + File.separator + cmf.getOriginalFilename()));
				System.out.println("Upload thành công!");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

		}
		objN.setPicture(cmf.getOriginalFilename());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userinfo = userDao.getItem(auth.getName());
		objN.setId_user(userinfo.getId_user());

		if (newsDao.addItem(objN) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/news";
	}

	@RequestMapping(value = "/news/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra, HttpServletRequest request) {
		String fileName = newsDao.getItem(id).getPicture();
		if (!fileName.isEmpty()) {
			// có hình ảnh => xóa ảnh
			String filePath = request.getServletContext().getRealPath("/files") + File.separator + fileName;
			System.out.println(filePath);
			File file = new File(filePath);
			file.delete();
		}

		if (newsDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/news";
	}

	@RequestMapping(value = "/news/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objN", newsDao.getItem(id));
		return "admin.news.edit";
	}

	@RequestMapping(value = "/news/edit/{id_news}", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("objN") News objN, BindingResult rs,
			@RequestParam("hinhanh") CommonsMultipartFile cmf,
			@RequestParam(value = "delPic", required = false) String delPic, RedirectAttributes ra,
			HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/news/edit/" + objN.getId_news();
		}
		News oldNews = newsDao.getItem(objN.getId_news());
		String filename = cmf.getOriginalFilename();
		if (!filename.isEmpty()) {
			if (!oldNews.getPicture().isEmpty()) {
				// xóa ảnh cũ
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldNews.getPicture();
				File file = new File(filePath);
				file.delete();
			}
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + DIR_UPLOAD;
			System.out.println(dirPath);
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			try {
				cmf.transferTo(new File(dirPath + File.separator + filename));
				System.out.println("Upload thành công!");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			if ((delPic != null) && (!oldNews.getPicture().isEmpty())) {
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldNews.getPicture();
				File file = new File(filePath);
				file.delete();
			} else if (delPic == null) {
				filename = oldNews.getPicture();
			}
		}
		objN.setPicture(filename);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userinfo = userDao.getItem(auth.getName());
		objN.setId_user(userinfo.getId_user());
		if (newsDao.editItem(objN) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/news";
	}
	
	@RequestMapping("/news/search")
	public String search(@RequestParam("searchText") String searchText, @RequestParam(value="searchCat", defaultValue="0") int idCat, @RequestParam(value="page", defaultValue="1") Integer page, ModelMap modelMap) {
		if (page == null || page < 1) {
			page = 1;
		}
		int offset = (page - 1) * Defines.ROW_COUNT_ADMIN;
		int sumNews = 0;
		int sumPage = 1;
		if (idCat == 0) {
			sumNews = newsDao.countsumNewsBySearch(searchText);
			sumPage = (int) Math.ceil((double) sumNews / Defines.ROW_COUNT_ADMIN);
			modelMap.addAttribute("listNews", newsDao.getItemsBySearch(searchText, offset));
		} else {
			sumNews = newsDao.countsumNewsBySearch(searchText, idCat);
			sumPage = (int) Math.ceil((double) sumNews / Defines.ROW_COUNT_ADMIN);
			modelMap.addAttribute("listNews", newsDao.getItemsByIdCatSearch(searchText, idCat, offset));
		}
		Message msg;
		if (sumNews > 0) {
			msg = new Message(1, "Tìm được "+sumNews+" kết quả!");
		} else {
			msg = new Message(0, "Không tìm thấy kết quả nào!");
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
		modelMap.addAttribute("msg", msg);
		return "admin.news.search";
	}

}
