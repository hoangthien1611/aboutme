package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.CommentDao;
import entity.Message;

@Controller
@RequestMapping("/admincp")
public class AdminCommentController {
	@Autowired
	private CommentDao commentDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Bình luận");
	}
	
	@RequestMapping(value = { "/comment/{page}", "/comment" })
	public String index(@PathVariable(value = "page", required = false) Integer page, ModelMap modelMap) {
		if (page == null || page < 1) {
			page = 1;
		}
		
		int sumComment = commentDao.countComment();
		int sumPage = (int) Math.ceil((double) sumComment / Defines.ROW_COUNT_ADMIN);
		int offset = (page - 1) * Defines.ROW_COUNT_ADMIN;
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
		modelMap.addAttribute("listComment", commentDao.getItems(offset));
		return "admin.comment.index";
	}
	
	@RequestMapping(value = { "/comment/{page}", "/comment" }, method=RequestMethod.POST)
	public @ResponseBody String index(@RequestParam("aid") int id, @RequestParam("agt") int gt, HttpServletRequest request) {
		if (commentDao.changeStatus(id, gt) > 0) {
			if (gt == 1) {
				return "<a href='javascript:void(0)' onclick='changeActive(" + id + ", 0)'><img src='"+ request.getContextPath() +"/templates/admin/img/active.png' width='20px'></a>";
			} else {
				return "<a href='javascript:void(0)' onclick='changeActive(" + id + ", 1)'><img src='"+ request.getContextPath() +"/templates/admin/img/disactive.png' width='20px'></a>";
			}
		} else {
			return "Thất bại";
		}
	}
	
	@RequestMapping(value = "/comment/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (commentDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/comment";
	}

	@RequestMapping(value="/comment/delall")
	public String delAll(HttpServletRequest request, RedirectAttributes ra) {
		try {
			if(request.getParameterValues("id_comment") != null) {
				for (String index : request.getParameterValues("id_comment")) {
					int cid = Integer.parseInt(index);
					if(commentDao.delItem(cid) > 0) {
						Message msg = new Message(1, "Xóa thành công!");
						ra.addFlashAttribute("msg", msg);
					} else {
						Message msg = new Message(0, "Xóa thất bại!");
						ra.addFlashAttribute("msg", msg);
					}
				}
			}
			return "redirect:/admincp/comment";
		} catch (Exception e) {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
			return "redirect:/admincp/comment";
		}
	}
}
