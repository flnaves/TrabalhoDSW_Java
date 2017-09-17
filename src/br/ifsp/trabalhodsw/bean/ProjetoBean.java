package br.ifsp.trabalhodsw.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ifsp.trabalhodsw.entities.Projeto;
import br.ifsp.trabalhodsw.repository.Repository;

@ManagedBean
@SessionScoped
public class ProjetoBean {

	private Projeto projeto = new Projeto();

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String gravar() {

		if (projeto.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("nome", new FacesMessage("Projeto deve conter um nome"));
			return "cadastro_projeto";
		}

		if (projeto.getId() != null && projeto.getId().intValue() > 0) {
			new Repository<Projeto>(Projeto.class).atualiza(this.projeto);
			return "lista_projeto?faces-redirect=true";
		} else {
			new Repository<Projeto>(Projeto.class).adiciona(this.projeto);
			return "lista_projeto?faces-redirect=true";
		}
		

	}

	public List<Projeto> getListaProjetos() {
		return new Repository<Projeto>(Projeto.class).listaTodos();
	}
	
	public String adicionarVoluntario(Projeto projeto) {
		this.projeto = projeto;
		return "adicionar_voluntarios";
	}

}
