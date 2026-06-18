package cadastro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView inicio() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		
		andView.addObject("pessoaObj",new Pessoa());
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.POST ,value = "**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> pessoas = pessoaRepository.findAll();
		mv.addObject("pessoaObj",new Pessoa());
		mv.addObject("pessoas", pessoas);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView listarPessoas() {
		ModelAndView mv = new ModelAndView("cadastro/cadastropessoa");
		List<Pessoa> pessoas = pessoaRepository.findAll();
		mv.addObject("pessoas", pessoas);
		mv.addObject("pessoaObj",new Pessoa());
		
		return mv;
	}
	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView verEditar( @PathVariable("idpessoa") Long idpessoa ) {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Optional<Pessoa> pessoaObj = pessoaRepository.findById(idpessoa);
		andView.addObject("pessoaObj",pessoaObj.get());
		return andView;
	}
	
	@GetMapping("/excluir/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		pessoaRepository.deleteById(idpessoa);
		List<Pessoa> listar = pessoaRepository.findAll();
		andView.addObject("pessoas", listar);
		andView.addObject("pessoaObj", new Pessoa());
		return andView;
	}
	


}
