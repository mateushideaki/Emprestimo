package br.com.emprestimo.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.emprestimo.entity.Pessoa;
import br.com.emprestimo.util.HibernateUtil;


public class PessoaDao {
	
		private HibernateUtil hibernateUtil = new HibernateUtil();
		private Session session;
		private int pessoaDAOid;

		public Integer save(Pessoa pessoa) {

			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			pessoaDAOid = (Integer) session.save(pessoa);
			session.getTransaction().commit();
			session.close();
			return pessoaDAOid;

		}

		public Pessoa buscaPorMatricula(Integer matricula) {
			session = hibernateUtil.getSessionFactory().openSession();
			Pessoa pessoa = null;

			session.beginTransaction();
			pessoa = (Pessoa) session.get(Pessoa.class, matricula);
			try {
				pessoa.setNome(pessoa.getNome());
				pessoa.setEndereco(pessoa.getEndereco());
				pessoa.setTelefone(pessoa.getTelefone());
			} catch (NullPointerException e) {
				System.out.println(e);
			} finally {
				session.close();
			}

			return pessoa;
		}

		public void delete(Integer matricula) {
			session = hibernateUtil.getSessionFactory().openSession();

			Pessoa pessoa = null;
			try {
				
				session.beginTransaction();
				pessoa = (Pessoa) session.load(Pessoa.class, matricula);
				if (pessoa != null) {
					session.delete(pessoa);
					session.getTransaction().commit();
				}
				
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				session.close();
			}
		}

		public List<Pessoa> listPessoas() {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "FROM Pessoa p ORDER BY p.nome ASC";
			List<Pessoa> pessoas = session.createQuery(hql).list();
			System.out.println(pessoas);
			session.getTransaction().commit();
			session.close();
			return pessoas;
		}
		
		public void atualizar(Pessoa pessoaUpdate,  String nome, String endereco, String telefone) {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			pessoaUpdate = (Pessoa) session.get(Pessoa.class, pessoaUpdate.getMatricula());
			pessoaUpdate.setNome(nome);
			pessoaUpdate.setEndereco(endereco);
			pessoaUpdate.setTelefone(telefone);
			session.save(pessoaUpdate);
			session.getTransaction().commit();
			session.close();
		}
	}
