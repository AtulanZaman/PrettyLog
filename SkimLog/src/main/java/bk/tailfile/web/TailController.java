package bk.tailfile.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/files")
public class TailController {
	String filename = "C:/Work/log/out.txt";

	@RequestMapping("/home")
	public String home(Model model) {
		return "files/home";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(
		@RequestParam("filename") String filename
	){
		System.out.println(filename);
		this.filename = filename;
		return "files/home";
	}
}
