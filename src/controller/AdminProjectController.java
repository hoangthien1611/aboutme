package controller;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.ProjectDao;
import entity.Message;
import entity.Project;

@Controller
@RequestMapping("/admincp")
public class AdminProjectController {
	public static final String DIR_UPLOAD = "files";
	@Autowired
	private ProjectDao projectDao;
	
	@ModelAttribute
	public void addCommonObjects(ModelMap modelMap) {
		modelMap.addAttribute("title", "Admin - Dự án");
	}
	
	@RequestMapping("/project")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("listProject", projectDao.getItems());
		return "admin.project.index";
	}
	
	@RequestMapping(value = "/project/add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		return "admin.project.add";
	}
	
	@RequestMapping(value = "/project/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objPro") Project objPro, BindingResult rs, @RequestParam("hinhanh") CommonsMultipartFile cmf,
			RedirectAttributes ra, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "admin.project.add";
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
		objPro.setPicture(cmf.getOriginalFilename());

		if (projectDao.addItem(objPro) > 0) {
			Message msg = new Message(1, "Thêm thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Thêm thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/project";
	}

	@RequestMapping(value = "/project/del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra, HttpServletRequest request) {
		String fileName = projectDao.getItem(id).getPicture();
		if (!fileName.isEmpty()) {
			// có hình ảnh => xóa ảnh
			String filePath = request.getServletContext().getRealPath("/files") + File.separator + fileName;
			System.out.println(filePath);
			File file = new File(filePath);
			file.delete();
		}

		if (projectDao.delItem(id) > 0) {
			Message msg = new Message(1, "Xóa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Xóa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/project";
	}
	
	@RequestMapping(value = "/project/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("objPro", projectDao.getItem(id));
		return "admin.project.edit";
	}
	
	@RequestMapping(value = "/project/edit/{id_project}", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("objP") Project objP, BindingResult rs, @RequestParam("hinhanh") CommonsMultipartFile cmf, @RequestParam(value="delPic", required=false) String delPic, 
			RedirectAttributes ra, HttpServletRequest request) {
		if (rs.hasErrors()) {
			return "redirect:/admincp/project/edit/"+objP.getId_project();
		}
		Project oldPro = projectDao.getItem(objP.getId_project());
		String filename = cmf.getOriginalFilename();
		if (!filename.isEmpty()) {
			if (!oldPro.getPicture().isEmpty()) {
				// xóa ảnh cũ
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldPro.getPicture();
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
			if ((delPic != null) && (!oldPro.getPicture().isEmpty())) {
				String filePath = request.getServletContext().getRealPath("/files") + File.separator
						+ oldPro.getPicture();
				File file = new File(filePath);
				file.delete();
			} else if (delPic == null) {
				filename = oldPro.getPicture();
			}
		}
		objP.setPicture(filename);
		if (projectDao.editItem(objP) > 0) {
			Message msg = new Message(1, "Sửa thành công!");
			ra.addFlashAttribute("msg", msg);
		} else {
			Message msg = new Message(0, "Sửa thất bại!");
			ra.addFlashAttribute("msg", msg);
		}
		return "redirect:/admincp/project";
	}
}
