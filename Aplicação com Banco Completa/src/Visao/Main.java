package Visao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Conexao.FabricaConexao;


public class Main {
	public static void main(String[] args)throws SQLException {
		Scanner teclado = new Scanner(System.in);
		Connection conexao = FabricaConexao.getConexao("academia");
		
		while(true) {
			System.out.println("--------------------------------");
			System.out.println("      Bem vindo a academia      ");
			System.out.println("       O que deseja fazer?      ");
			System.out.println("Voc� � aluno ou funcionario?");
			System.out.println("0. Aluno");
			System.out.println("1. Funcionario");
			System.out.println("2. Sair do programa");
			System.out.println("--------------------------------");
			System.out.println("Digite sua resposta: ");
			int resp = teclado.nextInt();
			
			if(resp == 0) {
				//Voc� � um aluno
				teclado.nextLine();
				System.out.println("--------------------------------");
				System.out.println("Ol�, digite seu nome e sua senha:");
				System.out.println("--------------------------------");
				System.out.println("Seu nome: ");
				String nome = teclado.nextLine();
				System.out.println("Sua senha: ");
				int senha = teclado.nextInt();
				
				PreparedStatement stmt = conexao.prepareStatement("select * from alunos where nome = ? and senha = ?");
				stmt.setString(1, nome);
				stmt.setInt(2, senha);
				
				ResultSet rs = stmt.executeQuery();
				int codigoDoUsuario = 0;
				String nomeDoUsuario = "";
				
				while(rs.next()) {
						codigoDoUsuario = rs.getInt("codigo");
						nomeDoUsuario = rs.getString("nome");
				}
				
				while(true){
					if(nomeDoUsuario.length() >3) {
						System.out.println("Bem vindo " + nomeDoUsuario);
						System.out.println("O que deseja fazer?");
						System.out.println("0. Voltar par o menu");
						System.out.println("Digite sua resposta: ");
						int resp3 = teclado.nextInt();
						
						if(resp3 == 0) {
							System.out.println("Voltando para o menu...");
							break;
						}
					}else {
						System.out.println("Este aluno n�o existe");
						break;
					}
				}
				
			}else if(resp == 1) {
				//Voce � um funcionario
				teclado.nextLine();
				System.out.println("--------------------------------");
				System.out.println("Ol�, digite seu nome e sua senha:");
				System.out.println("--------------------------------");
				System.out.println("Seu nome: ");
				String nome = teclado.nextLine();
				System.out.println("Sua senha: ");
				int senha = teclado.nextInt();
				
				//Descobrir se voc� � mesmo um funcionario
				PreparedStatement stmt = conexao.prepareStatement("select * from funcionarios where nome = ? and senha = ?");
				stmt.setString(1, nome);
				stmt.setInt(2, senha);
				
				ResultSet rs = stmt.executeQuery();
				
				int codigoDoFunc = 0;
				String nomeDoFunc = "";
				while(rs.next()) {
					codigoDoFunc = rs.getInt("codigo");
					nomeDoFunc = rs.getString("nome");
				}
				
				while(true) {
					//Dentro de funcionario
					if(nomeDoFunc .length() > 3) {
						System.out.println("--------------------------------");
						System.out.println("Bem vindo " + nomeDoFunc);
						System.out.println("O que deseja fazer");
						System.out.println("0. Voltar para o menu");
						System.out.println("1. Adicionar aluno");
						System.out.println("2. Remover aluno");
						System.out.println("3. Ver todos os alunos");
						System.out.println("4. Adicionar um novo funcionario");
						System.out.println("--------------------------------");
						System.out.println("Digite sua resposta:");
						int resp2 = teclado.nextInt();

						if(resp2 == 0) {
							//Voltando para o menu
							System.out.println("Voltando para o menu...");
							break;
						}else if (resp2 == 1) {
							//Adicionando um aluno
							teclado.nextLine();
							System.out.println("---------------------------");
							System.out.println("Digite um nome para o aluno");
							System.out.println("---------------------------");
							nome = teclado.nextLine();
							System.out.println("-----------------------------");
							System.out.println("Digite uma senha para o aluno");
							System.out.println("-----------------------------");

							senha = teclado.nextInt();
							
							stmt = conexao.prepareStatement("insert into alunos(nome, senha) values (?,?)");
							stmt.setString(1, nome);
							stmt.setInt(2, senha);
							
							int ad = stmt.executeUpdate();
							
							if(ad == 1) {
								System.out.println("Aluno adicionado com sucesso");
							}
							
						}else if (resp2 == 2) {
							//Removendo um aluno
							teclado.nextLine();
							System.out.println("----------------------------");
							System.out.println("Digite o nome do aluno para remove-lo");
							System.out.println("----------------------------");
							nome = teclado.nextLine();
							
							stmt = conexao.prepareStatement("delete from alunos where nome = ?");
							stmt.setString(1, nome);
							
							int dele = stmt.executeUpdate();
							
							if(dele == 1) {
								System.out.println("Aluno removido com sucesso");
							}else {
								System.out.println("Aluno n�o encontrado!!");
							}
									
						}else if(resp2 == 3) {
							teclado.nextLine();
							
							stmt = conexao.prepareStatement("select * from alunos");
							rs = stmt.executeQuery();
							
							while(rs.next()) {
								int codigo = rs.getInt("codigo");
								nome = rs.getString("nome");
								
								System.out.println("Codigo: " + codigo);
								System.out.println("Nome: " + nome);
								System.out.println("-------------------------");
							}
							
							
						}else if (resp2 == 4) {
							teclado.nextLine();
							
							System.out.println("---------------------------");
							System.out.println("Digite um nome para o funcionario");
							System.out.println("---------------------------");
							nome = teclado.nextLine();
							System.out.println("-----------------------------");
							System.out.println("Digite uma senha para o funcionario");
							System.out.println("-----------------------------");
							senha = teclado.nextInt();
							
							stmt = conexao.prepareStatement("insert into funcionarios(nome, senha) values (?,?)");
							stmt.setString(1, nome);
							stmt.setInt(2, senha);
							
							int ad = stmt.executeUpdate();
							
							if(ad == 1) {
								System.out.println("Funcionario adicionado com sucesso");
							}
							
						}
						
					}else {
						System.out.println("----------------------------");
						System.out.println("Este funcionario n�o existe");
						System.out.println("----------------------------");
						break;
					}
				}
				
			}else if (resp == 2) {
				System.out.println("Finalizando o programa...");
				break;
			}else
				System.out.println("Digite um n�mero valido!");
		}
		
		conexao.close();
		teclado.close();
	}
}
