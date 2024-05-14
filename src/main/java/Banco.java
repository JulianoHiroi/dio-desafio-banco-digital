package main.java;
import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nome;
	private List<Conta> contas;
	
	public Banco(String nome){
		this.nome = nome;
		contas = new ArrayList<Conta>();
	}

	public String getNome() {
		return nome;
	}
	public List<Conta> getContas() {
		return contas;
	}
	public List<Conta> getContasPorNome(Cliente cliente){
		return contas.stream().filter((conta) -> conta.getCliente() == cliente ).toList();
	}
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}

}

