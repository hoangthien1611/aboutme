package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.RoleDao;
import dao.UserDao;
import entity.Message;
import entity.User;

@Controller
@RequestMapping("/admincp")
public class AdminUserController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@RequestMapping("/getUser")
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User userinfo = userDao.getItem(name);
	    return userinfo;
	}
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "AdminCP - Người dùng");
		modelMap.addAttribute("listRole", roleDao.getItems());
	    modelMap.addAttribute("userinfo", getUser());
	}
	
	@RequestMapping(value = "/user")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listUser", userDao.getItems());
		return "admin.user.index";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		return "admin.user.add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objUser") User objUser, BindingResult rs,RedirectAttributes ra) {
		if (rs.hasErrors()) {
			return "admin.user.add";
		}
		String regex = "^[a-zA-Z0-9_.-]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(objUser.getUsername());
		boolean match = matcher.matches();
		if (match == false) {
			ra.addFlashAttribute("msg", "Tên đăng nhập không đúng định dạng!");
			return "redirect:/admincp/user/add";
		} else {
			if (userDao.checkItem(objUser.getUsername()) >  0) {
				ra.addFlashAttribute("msg", "Tên đăng nhập đã trùng. Vui lòng nhập lại!");
				return "redirect:/admincp/user/add";
			} else {
				objUser.setPassword(encoder.encode(objUser.getPassword()));
				if (userDao.addItem(objUser) > 0) {
					Message msg = new Message(1, "Thêm thành công!");
					ra.addFlashAttribute("msg", msg);
				} else {
					Message msg = new Message(0, "Thêm thất bại!");
					ra.addFlashAttribute("msg", msg);
				}
				return "redirect:/admincp/user";
			}
		}
	}
	
	@RequestMapping(value = "/user/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (userDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/user";
	}
	
	@RequestMapping(value = "/user/edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		if ((getUser().getId_user() != id) && (getUser().getId_role() !=1)) {
			Message msg = new Message(0, "Bạn không có quyền chỉnh sửa!");
			modelMap.addAttribute("msg", msg);
			return "admin.user.index";
		}
		User user = userDao.getItem(id);
		
		modelMap.addAttribute("objUser", user);
		return "admin.user.edit";
	}

	@RequestMapping(value = "/user/edit/{id_user}", method = RequestMethod.POST)
	public String edit(@PathVariable("id_user") int id,@ModelAttribute("objUser") User objUser, RedirectAttributes ra) {
		
		if ("".equals(objUser.getPassword())) {
			User oldUser = userDao.getItem(id);
			objUser.setPassword(oldUser.getPassword());
		} else {
			objUser.setPassword(encoder.encode(objUser.getPassword()));
		}
		if (userDao.editItem(objUser) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/user";
	}
}
