package cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cadastro.model.Pessoa;
import cadastro.repository.PessoaRepository;
@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(method = RequestMethod.GET,value = "/cadastropessoa")
	public String inicio() {
		return "cadastro/cadastropessoa";
	}
	
	@RequestMapping(method = RequestMethod.POST ,value = "/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> pessoas = pessoaRepository.findAll();
		mv.addObject("pessoas", pessoas);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView listarPessoas() {
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> pessoas = pessoaRepository.findAll();
		mv.addObject("pessoas", pessoas);
		
		return mv;
	}
	


}
