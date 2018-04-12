package br.com.emprestimo.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.emprestimo.entity.Material;
import br.com.emprestimo.entity.TipoMaterial;
import br.com.emprestimo.util.HibernateUtil;


public class MaterialDao {
	
		private HibernateUtil hibernateUtil = new HibernateUtil();
		private Session session;
		private int materialDAOid;

		public Integer save(Material material) {

			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			materialDAOid = (Integer) session.save(material);
			session.getTransaction().commit();
			session.close();
			return materialDAOid;

		}

		public Material buscaPorMatricula(Integer codigo) {
			session = hibernateUtil.getSessionFactory().openSession();
			Material material = null;

			session.beginTransaction();
			material = (Material) session.get(Material.class, codigo);
			try {
				material.setNomeMaterial(material.getNomeMaterial());
				material.setTipo(material.getTipo());
			} catch (NullPointerException e) {
				System.out.println(e);
			} finally {
				session.close();
			}

			return material;
		}

		public void delete(Integer codigo) {
			session = hibernateUtil.getSessionFactory().openSession();

			Material material = null;
			try {
				
				session.beginTransaction();
				material = (Material) session.load(Material.class, codigo);
				if (material != null) {
					session.delete(material);
					session.getTransaction().commit();
				}
				
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				session.close();
			}
		}

		public List<Material> listMaterials() {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "FROM Material m ORDER BY m.nomeMaterial ASC";
			List<Material> materials = session.createQuery(hql).list();
			System.out.println(materials);
			session.getTransaction().commit();
			session.close();
			return materials;
		}
		
		public void atualizar(Material materialUpdate,  String nome, TipoMaterial tipo) {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			materialUpdate = (Material) session.get(Material.class, materialUpdate.getCodigo());
			materialUpdate.setNomeMaterial(nome);
			materialUpdate.setTipo(tipo);
			materialUpdate.setDataCadastro(new java.util.Date());
			session.save(materialUpdate);
			session.getTransaction().commit();
			session.close();
		}
	}
