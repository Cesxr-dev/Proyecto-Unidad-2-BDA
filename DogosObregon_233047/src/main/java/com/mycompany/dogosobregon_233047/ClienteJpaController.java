/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dogosobregon_233047;

import com.mycompany.dogosobregon_233047.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.Cliente;
import models.Pedido;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author David TL
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getPedidos() == null) {
            cliente.setPedidos(new HashSet<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteRecomienda = cliente.getClienteRecomienda();
            if (clienteRecomienda != null) {
                clienteRecomienda = em.getReference(clienteRecomienda.getClass(), clienteRecomienda.getId());
                cliente.setClienteRecomienda(clienteRecomienda);
            }
            Set<Pedido> attachedPedidos = new HashSet<Pedido>();
            for (Pedido pedidosPedidoToAttach : cliente.getPedidos()) {
                pedidosPedidoToAttach = em.getReference(pedidosPedidoToAttach.getClass(), pedidosPedidoToAttach.getId());
                attachedPedidos.add(pedidosPedidoToAttach);
            }
            cliente.setPedidos(attachedPedidos);
            em.persist(cliente);
            if (clienteRecomienda != null) {
                Cliente oldClienteRecomiendaOfClienteRecomienda = clienteRecomienda.getClienteRecomienda();
                if (oldClienteRecomiendaOfClienteRecomienda != null) {
                    oldClienteRecomiendaOfClienteRecomienda.setClienteRecomienda(null);
                    oldClienteRecomiendaOfClienteRecomienda = em.merge(oldClienteRecomiendaOfClienteRecomienda);
                }
                clienteRecomienda.setClienteRecomienda(cliente);
                clienteRecomienda = em.merge(clienteRecomienda);
            }
            for (Pedido pedidosPedido : cliente.getPedidos()) {
                Cliente oldClienteOfPedidosPedido = pedidosPedido.getCliente();
                pedidosPedido.setCliente(cliente);
                pedidosPedido = em.merge(pedidosPedido);
                if (oldClienteOfPedidosPedido != null) {
                    oldClienteOfPedidosPedido.getPedidos().remove(pedidosPedido);
                    oldClienteOfPedidosPedido = em.merge(oldClienteOfPedidosPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            Cliente clienteRecomiendaOld = persistentCliente.getClienteRecomienda();
            Cliente clienteRecomiendaNew = cliente.getClienteRecomienda();
            Set<Pedido> pedidosOld = persistentCliente.getPedidos();
            Set<Pedido> pedidosNew = cliente.getPedidos();
            if (clienteRecomiendaNew != null) {
                clienteRecomiendaNew = em.getReference(clienteRecomiendaNew.getClass(), clienteRecomiendaNew.getId());
                cliente.setClienteRecomienda(clienteRecomiendaNew);
            }
            Set<Pedido> attachedPedidosNew = new HashSet<Pedido>();
            for (Pedido pedidosNewPedidoToAttach : pedidosNew) {
                pedidosNewPedidoToAttach = em.getReference(pedidosNewPedidoToAttach.getClass(), pedidosNewPedidoToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidoToAttach);
            }
            pedidosNew = attachedPedidosNew;
            cliente.setPedidos(pedidosNew);
            cliente = em.merge(cliente);
            if (clienteRecomiendaOld != null && !clienteRecomiendaOld.equals(clienteRecomiendaNew)) {
                clienteRecomiendaOld.setClienteRecomienda(null);
                clienteRecomiendaOld = em.merge(clienteRecomiendaOld);
            }
            if (clienteRecomiendaNew != null && !clienteRecomiendaNew.equals(clienteRecomiendaOld)) {
                Cliente oldClienteRecomiendaOfClienteRecomienda = clienteRecomiendaNew.getClienteRecomienda();
                if (oldClienteRecomiendaOfClienteRecomienda != null) {
                    oldClienteRecomiendaOfClienteRecomienda.setClienteRecomienda(null);
                    oldClienteRecomiendaOfClienteRecomienda = em.merge(oldClienteRecomiendaOfClienteRecomienda);
                }
                clienteRecomiendaNew.setClienteRecomienda(cliente);
                clienteRecomiendaNew = em.merge(clienteRecomiendaNew);
            }
            for (Pedido pedidosOldPedido : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedido)) {
                    pedidosOldPedido.setCliente(null);
                    pedidosOldPedido = em.merge(pedidosOldPedido);
                }
            }
            for (Pedido pedidosNewPedido : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedido)) {
                    Cliente oldClienteOfPedidosNewPedido = pedidosNewPedido.getCliente();
                    pedidosNewPedido.setCliente(cliente);
                    pedidosNewPedido = em.merge(pedidosNewPedido);
                    if (oldClienteOfPedidosNewPedido != null && !oldClienteOfPedidosNewPedido.equals(cliente)) {
                        oldClienteOfPedidosNewPedido.getPedidos().remove(pedidosNewPedido);
                        oldClienteOfPedidosNewPedido = em.merge(oldClienteOfPedidosNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Cliente clienteRecomienda = cliente.getClienteRecomienda();
            if (clienteRecomienda != null) {
                clienteRecomienda.setClienteRecomienda(null);
                clienteRecomienda = em.merge(clienteRecomienda);
            }
            Set<Pedido> pedidos = cliente.getPedidos();
            for (Pedido pedidosPedido : pedidos) {
                pedidosPedido.setCliente(null);
                pedidosPedido = em.merge(pedidosPedido);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
