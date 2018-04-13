package br.com.emprestimo.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.emprestimo.entity.Emprestimo;
import br.com.emprestimo.entity.Material;
import br.com.emprestimo.entity.Pessoa;
import br.com.emprestimo.util.HibernateUtil;

public class EmprestimoDao {
	private HibernateUtil hibernateUtil = new HibernateUtil();
	private Session session;
	private int emprestimoDAOid;

	public Integer save(Emprestimo emprestimo) {

		session = hibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		emprestimoDAOid = (Integer) session.save(emprestimo);
		session.getTransaction().commit();
		session.close();
		return emprestimoDAOid;

	}

	public Emprestimo buscaPorCodigo(Integer codigo) {
		session = hibernateUtil.getSessionFactory().openSession();
		Emprestimo emprestimo = null;

		session.beginTransaction();
		emprestimo = (Emprestimo) session.get(Emprestimo.class, codigo);
		try {
			emprestimo.setPessoa(emprestimo.getPessoa());
			emprestimo.setMaterial(emprestimo.getMaterial());
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			session.close();
		}

		return emprestimo;
	}

	public boolean delete(Integer codigo) {
		boolean deletou = false;
		session = hibernateUtil.getSessionFactory().openSession();

		Emprestimo emprestimo = null;
		try {

			session.beginTransaction();
			emprestimo = (Emprestimo) session.load(Emprestimo.class, codigo);
			if (emprestimo != null) {
				session.delete(emprestimo);
				session.getTransaction().commit();
				deletou = true;
			}

		} catch (Exception e) {
			System.out.println(e);
			deletou = false;
		} finally {
			session.close();
		}

		return deletou;
	}

	public List<Emprestimo> listEmprestimos() {
		session = hibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Emprestimo m";
		List<Emprestimo> emprestimos = session.createQuery(hql).list();
		System.out.println(emprestimos);
		session.getTransaction().commit();
		session.close();
		return emprestimos;
	}

	public void atualizar(Emprestimo emprestimoUpdate, Pessoa pessoa, Material material) {
		session = hibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		emprestimoUpdate = (Emprestimo) session.get(Emprestimo.class, emprestimoUpdate.getId());
		emprestimoUpdate.setPessoa(pessoa);
		emprestimoUpdate.setMaterial(material);
		session.save(emprestimoUpdate);
		session.getTransaction().commit();
		session.close();
	}
}
