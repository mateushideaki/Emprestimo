package br.com.emprestimo.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.emprestimo.entity.TipoMaterial;
import br.com.emprestimo.util.HibernateUtil;


public class TipoMaterialDao {
	
		private HibernateUtil hibernateUtil = new HibernateUtil();
		private Session session;
		private int tipoDaoid;

		public Integer save(TipoMaterial tipoMaterial) {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tipoDaoid = (Integer) session.save(tipoMaterial);
			session.getTransaction().commit();
			session.close();
			return tipoDaoid;

		}

		public TipoMaterial buscaPorId(Integer id) {
			session = hibernateUtil.getSessionFactory().openSession();
			TipoMaterial tipoMaterial = null;

			session.beginTransaction();
			tipoMaterial = (TipoMaterial) session.get(TipoMaterial.class, id);
			try {
				tipoMaterial.setNomeTipo(tipoMaterial.getNomeTipo());
			} catch (NullPointerException e) {
				System.out.println(e);
			} finally {
				session.close();
			}

			return tipoMaterial;
		}

		public boolean delete(Integer id) {
			boolean deletou = false;
			session = hibernateUtil.getSessionFactory().openSession();

			TipoMaterial tipoMaterial = null;
			try {
				
				session.beginTransaction();
				tipoMaterial = (TipoMaterial) session.load(TipoMaterial.class, id);
				if (tipoMaterial != null) {
					session.delete(tipoMaterial);
					session.getTransaction().commit();
					deletou = true;
				}
				
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				session.close();
			}
			
			return deletou;
		}

		public List<TipoMaterial> listtipoMaterials() {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "FROM TipoMaterial t ORDER BY t.nomeTipo ASC";
			List<TipoMaterial> tipoMaterials = session.createQuery(hql).list();
			session.getTransaction().commit();
			session.close();
			return tipoMaterials;
		}
		
		public void atualizar(TipoMaterial tipoMaterialUpdate,  String nome) {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tipoMaterialUpdate = (TipoMaterial) session.get(TipoMaterial.class, tipoMaterialUpdate.getId());
			tipoMaterialUpdate.setNomeTipo(nome);

			session.save(tipoMaterialUpdate);
			session.getTransaction().commit();
			session.close();
		}
	}
