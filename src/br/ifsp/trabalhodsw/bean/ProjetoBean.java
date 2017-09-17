package br.ifsp.trabalhodsw.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.ifsp.trabalhodsw.entities.Projeto;
import br.ifsp.trabalhodsw.repository.Repository;

@ManagedBean
public class ProjetoBean {

	private Projeto projeto = new Projeto();

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public void gravar() {

		if (projeto.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("nome", new FacesMessage("Projeto deve conter um nome"));
		}

		if (projeto.getId() != null && projeto.getId().intValue() > 0) {
			new Repository<Projeto>(Projeto.class).atualiza(this.projeto);
		} else {
			new Repository<Projeto>(Projeto.class).adiciona(this.projeto);
		}

	}

	public List<Projeto> getListaLivros() {
		return new Repository<Projeto>(Projeto.class).listaTodos();
	}

}
