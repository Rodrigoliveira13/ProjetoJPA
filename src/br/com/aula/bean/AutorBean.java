package br.com.aula.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.aula.dao.AutorDAO;
import br.com.aula.model.Autor;

@ManagedBean
public class AutorBean {
	AutorDAO aDao;
	private Autor autor;
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	private List<Autor> autores;
	
	public AutorBean() {
		autor = new Autor();
		aDao = new AutorDAO();
		autores = aDao.listar();
	}
	public void cadastrarAutor() {
		aDao = new AutorDAO();
		aDao.salvar(autor);
		autor = new Autor();
		FacesContext.getCurrentInstance().addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage("Cadastrado com Sucesso!"));
	}
	
	public void buscarPorId() {
		aDao = new AutorDAO();
		Autor aBusca = aDao.buscarPorId(autor.getId());
		autor = aBusca;
		if(aBusca == null) {
			FacesContext.getCurrentInstance().addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage("Autor não Encontrado!"));
		}
	}
	
	public void removerPorId() {
		aDao = new AutorDAO();
		Autor aBusca = aDao.buscarPorId(autor.getId());
		autor = aBusca;
		if(aBusca == null) {
			FacesContext.getCurrentInstance().addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage("Autor não Encontrado!"));
		}else {
			aDao.remover(autor.getId());
			FacesContext.getCurrentInstance().addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage("Autor excluido com sucesso!"));
		} 
	}

}
